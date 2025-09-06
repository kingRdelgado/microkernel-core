package co.unicauca.microkernel.plugin.manager;

import co.unicauca.microkernel.common.interfaces.IReportPlugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fábrica que carga dinámicamente los plugins usando reflexión.
 * Se apoya en el archivo plugin.properties (ubicado en resources).
 */
public class ReportPluginManager {

    private static final String FILE_NAME = "plugin.properties";
    private static ReportPluginManager instance;
    private Properties pluginProperties;

    private ReportPluginManager() {
        pluginProperties = new Properties();
    }

    public static ReportPluginManager getInstance() {
        return instance;
    }

    /**
     * Inicializa el gestor de plugins cargando el archivo de configuración.
     */
    public static void init(String basePath) throws Exception {
        instance = new ReportPluginManager();
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("No fue posible inicializar los plugins.");
        }
    }

    /**
     * Obtiene el plugin correspondiente a un tipo (html, json, etc.)
     */
    public IReportPlugin getReportPlugin(String type) {
        String propertyName = "report." + type.toLowerCase();
        if (!pluginProperties.containsKey(propertyName)) {
            return null;
        }

        String pluginClassName = pluginProperties.getProperty(propertyName);
        try {
            Class<?> pluginClass = Class.forName(pluginClassName);
            Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();
            if (pluginObject instanceof IReportPlugin) {
                return (IReportPlugin) pluginObject;
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            Logger.getLogger("ReportPluginManager")
                    .log(Level.SEVERE, "Error cargando plugin: " + type, e);
        }
        return null;
    }

    private void loadProperties(String basePath) {
        String filePath = basePath + FILE_NAME;
        try (FileInputStream stream = new FileInputStream(filePath)) {
            pluginProperties.load(stream);
        } catch (IOException e) {
            Logger.getLogger("ReportPluginManager")
                    .log(Level.SEVERE, "Error cargando archivo plugin.properties", e);
        }
    }
}

