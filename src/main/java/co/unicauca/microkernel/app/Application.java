package co.unicauca.microkernel.app;

import co.unicauca.microkernel.plugin.manager.ReportPluginManager;
import co.unicauca.microkernel.presentation.Console;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
        String basePath = getBaseFilePath();
        try {
            // Inicializar el manager (lee plugin.properties)
            ReportPluginManager.init(basePath);

            // Iniciar presentación
            Console console = new Console();
            console.start();

        } catch (Exception ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, "Error ejecutando la app", ex);
        }
    }

    /**
     * Obtiene la ruta base (funciona tanto en IDE como si se empaqueta en jar).
     */
    private static String getBaseFilePath() {
        try {
            String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8");
            File f = new File(path);
            if (f.isFile()) {
                String p = f.getParent();
                if (!p.endsWith(File.separator)) p += File.separator;
                return p;
            } else {
                // Ya es directorio (ej. target/classes)
                String p = f.getPath();
                if (!p.endsWith(File.separator)) p += File.separator;
                return p;
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, "Error en getBaseFilePath", ex);
            return null;
        }
    }
}

