package co.unicauca.microkernel.plugins.html;

import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.common.interfaces.IReportPlugin;

import java.util.List;

/**
 * Plugin ejemplo que genera un reporte HTML sencillo.
 */
public class HtmlReportPlugin implements IReportPlugin {

    @Override
    public String getName() {
        return "html";
    }

    @Override
    public String generateReport(List<Project> projects) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html>\n<html>\n<head><meta charset=\"utf-8\"><title>Report - Projects</title></head>\n<body>\n");
        sb.append("<h1>Reporte de Proyectos</h1>\n");
        sb.append("<table border='1' cellpadding='6' style='border-collapse:collapse;'>");
        sb.append("<tr><th>ID</th><th>Título</th><th>Modalidad</th><th>Director</th><th>Codirector</th><th>Fecha</th></tr>\n");
        for (Project p : projects) {
            sb.append("<tr>")
              .append("<td>").append(p.getId()).append("</td>")
              .append("<td>").append(escapeHtml(p.getTitle())).append("</td>")
              .append("<td>").append(escapeHtml(p.getModality())).append("</td>")
              .append("<td>").append(escapeHtml(p.getDirector())).append("</td>")
              .append("<td>").append(escapeHtml(p.getCodirector())).append("</td>")
              .append("<td>").append(escapeHtml(p.getDate())).append("</td>")
              .append("</tr>\n");
        }
        sb.append("</table>\n</body>\n</html>");
        return sb.toString();
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}

