package org.spigotmc.commonlib;

import java.io.IOException;
import java.util.Properties;

public class StartupCommands {

    static Properties properties = new Properties();
    static String startupCommands = properties.getProperty("startupCommands", null);

    public static void runStartupCommands() {
        if (startupCommands == null) {
            return;
        }

        try {
            SystemCommandExecutor.runSystemCommand(startupCommands);
        } catch (IOException | InterruptedException e) {
            // Do nothing if the command fails
        }
    }
}
