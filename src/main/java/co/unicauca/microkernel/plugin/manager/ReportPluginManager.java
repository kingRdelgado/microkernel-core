package co.unicauca.microkernel.plugin.manager;

import co.unicauca.microkernel.common.interfaces.IReportPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manager/fábrica que carga plugins de reporte definidas en plugin.properties.
 * El archivo plugin.properties puede encontrarse en la carpeta base de la app
 * o en classpath (src/main/resources/plugin.properties).
 *
 * Formato:
 *   report.html=co.unicauca.microkernel.plugins.html.HtmlReportPlugin
 *   report.json=co.unicauca.microkernel.plugins.json.JsonReportPlugin
 */
public class ReportPluginManager {

    private static final String FILE_NAME = "plugin.properties";
    private static ReportPluginManager instance;

    private Properties pluginProperties;
    private String basePath; // ruta base (opcional)

    private ReportPluginManager() {
        pluginProperties = new Properties();
    }

    public static ReportPluginManager getInstance() {
        return instance;
    }

    /**
     * Inicializa el manager leyendo plugin.properties desde basePath o classpath.
     * @param basePath ruta base (puede ser null)
     * @throws Exception si no logra inicializar plugins
     */
    public static void init(String basePath) throws Exception {
        instance = new ReportPluginManager();
        instance.basePath = basePath;
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("No se pudieron inicializar plugins (plugin.properties vacío o no encontrado)");
        }
    }

    /**
     * Devuelve la lista de plugins instanciados (usa reflexión).
     */
    public List<IReportPlugin> getReportPlugins() {
        List<IReportPlugin> plugins = new ArrayList<>();
        for (String key : pluginProperties.stringPropertyNames()) {
            String className = pluginProperties.getProperty(key).trim();
            try {
                Class<?> clazz = Class.forName(className);
                Object obj = clazz.getDeclaredConstructor().newInstance();
                if (obj instanceof IReportPlugin) {
                    plugins.add((IReportPlugin) obj);
                } else {
                    Logger.getLogger("ReportPluginManager").log(Level.WARNING,
                            "La clase {0} no implementa IReportPlugin", className);
                }
            } catch (Exception ex) {
                Logger.getLogger("ReportPluginManager").log(Level.SEVERE,
                        "Error cargando plugin " + className, ex);
            }
        }
        return plugins;
    }

    private void loadProperties(String basePath) {
        // 1) Intentar leer como archivo en basePath
        if (basePath != null) {
            String fp = basePath + FILE_NAME;
            try (FileInputStream fis = new FileInputStream(fp)) {
                pluginProperties.load(fis);
                return;
            } catch (IOException ex) {
                // no encontrado en disco, vamos a intentar classpath
            }
        }

        // 2) Intentar leer desde classpath (resources)
        try (InputStream is = getClass().getResourceAsStream("/" + FILE_NAME)) {
            if (is != null) {
                pluginProperties.load(is);
                return;
            }
        } catch (IOException ex) {
            Logger.getLogger("ReportPluginManager").log(Level.SEVERE, "Error leyendo plugin.properties desde classpath", ex);
        }

        // Si aquí no cargó, pluginProperties quedará vacío.
    }

    /**
     * Opcional: exponer basePath (para que ReportService pueda guardar archivos ahí)
     */
    public String getBasePath() {
        return basePath;
    }

}

