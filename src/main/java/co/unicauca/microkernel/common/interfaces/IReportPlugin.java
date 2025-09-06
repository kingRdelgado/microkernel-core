package co.unicauca.microkernel.common.interfaces;

import co.unicauca.microkernel.common.entities.Project;
import java.util.List;

/**
 * Contrato que deben implementar los plugins de reporte.
 */
public interface IReportPlugin {
    /**
     * Nombre corto del plugin (ej. "html", "json")
     */
    String getName();

    /**
     * Genera el reporte a partir de la lista de proyectos.
     * Retorna el contenido del reporte (por ejemplo HTML o JSON).
     */
    String generateReport(List<Project> projects) throws Exception;
}

