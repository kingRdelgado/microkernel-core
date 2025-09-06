package co.unicauca.microkernel.business;

import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.common.interfaces.IReportPlugin;
import co.unicauca.microkernel.plugin.manager.ReportPluginManager;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Servicio que orquesta la generación de reportes usando plugins.
 */
public class ReportService {

    private final ProjectService projectService;
    private final ReportPluginManager pluginManager;

    public ReportService() {
        this.projectService = new ProjectService();
        this.pluginManager = ReportPluginManager.getInstance();
    }

    /**
     * Genera todos los reportes (ejecuta todos los plugins configurados),
     * escribe cada reporte en la carpeta 'reports' bajo el basePath si está disponible,
     * y devuelve el contenido en consola.
     */
    public void generateAllReports() {
        List<Project> projects = projectService.getAll();
        List<IReportPlugin> plugins = pluginManager.getReportPlugins();

        String basePath = pluginManager.getBasePath();
        String outDir = (basePath != null) ? basePath + "reports" : "reports";

        // Crear carpeta si no existe
        File dir = new File(outDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (IReportPlugin plugin : plugins) {
            try {
                String content = plugin.generateReport(projects);

                // decidir extensión según nombre (convención)
                String ext = plugin.getName().toLowerCase();
                String fileName = "report_" + ext + "." + (ext.equals("html") ? "html" : "json");
                File outFile = new File(dir, fileName);

                try (FileWriter fw = new FileWriter(outFile, false)) {
                    fw.write(content);
                }

                System.out.println("Reporte generado por plugin '" + plugin.getName() + "': " + outFile.getAbsolutePath());
            } catch (Exception ex) {
                System.err.println("Error generando reporte con plugin " + plugin.getName() + ": " + ex.getMessage());
            }
        }
    }
}

