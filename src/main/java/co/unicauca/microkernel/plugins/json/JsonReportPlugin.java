package co.unicauca.microkernel.plugins.json;

import co.unicauca.microkernel.common.entities.Project;
import co.unicauca.microkernel.common.interfaces.IReportPlugin;

import java.util.List;

/**
 * Plugin ejemplo que genera un JSON muy simple.
 * No usamos librerías externas para mantener el ejemplo autónomo.
 */
public class JsonReportPlugin implements IReportPlugin {

    @Override
    public String getName() {
        return "json";
    }

    @Override
    public String generateReport(List<Project> projects) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < projects.size(); i++) {
            Project p = projects.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": ").append(p.getId()).append(",\n");
            sb.append("    \"title\": \"").append(escapeJson(p.getTitle())).append("\",\n");
            sb.append("    \"modality\": \"").append(escapeJson(p.getModality())).append("\",\n");
            sb.append("    \"director\": \"").append(escapeJson(p.getDirector())).append("\",\n");
            sb.append("    \"codirector\": \"").append(escapeJson(p.getCodirector())).append("\",\n");
            sb.append("    \"date\": \"").append(escapeJson(p.getDate())).append("\"\n");
            sb.append("  }");
            if (i < projects.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}

