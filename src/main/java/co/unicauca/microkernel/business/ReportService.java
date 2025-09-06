package co.unicauca.microkernel.business;

import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.common.interfaces.IReportPlugin;
import co.unicauca.microkernel.plugin.manager.ReportPluginManager;

import java.util.List;

/**
 * Servicio para generar reportes en distintos formatos (HTML, JSON).
 * Se delega la lógica a los plugins cargados dinámicamente.
 */
public class ReportService {

    /**
     * Genera un reporte en el formato especificado.
     *
     * @param projects lista de proyectos a reportar
     * @param type     tipo de reporte (ejemplo: "html", "json")
     * @return reporte en formato String
     * @throws Exception si no hay plugin disponible o si falla la generación
     */
    public String generateReport(List<Project> projects, String type) throws Exception {
        ReportPluginManager manager = ReportPluginManager.getInstance();
        IReportPlugin plugin = manager.getReportPlugin(type);

        if (plugin == null) {
            throw new Exception("No existe un plugin para el tipo: " + type);
        }
        return plugin.generateReport(projects);
    }
}


