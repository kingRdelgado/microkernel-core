package co.unicauca.microkernel.app;

import co.unicauca.microkernel.business.ProjectService;
import co.unicauca.microkernel.business.ReportService;
import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.plugin.manager.ReportPluginManager;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        try {
            // Inicializar gestor de plugins
            String basePath = getBaseFilePath();
            ReportPluginManager.init(basePath);

            // Obtener proyectos
            ProjectService projectService = new ProjectService();
            List<Project> projects = projectService.getAll();

            // Generar reportes
            ReportService reportService = new ReportService();

            String htmlReport = reportService.generateReport(projects, "html");
            System.out.println("=== REPORTE HTML ===");
            System.out.println(htmlReport);

            String jsonReport = reportService.generateReport(projects, "json");
            System.out.println("=== REPORTE JSON ===");
            System.out.println(jsonReport);

        } catch (Exception e) {
            System.err.println("Error ejecutando aplicación: " + e.getMessage());
        }
    }

    private static String getBaseFilePath() {
        try {
            String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            File pathFile = new File(path);
            if (pathFile.isFile()) {
                path = pathFile.getParent();
                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
            }
            return path;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}

