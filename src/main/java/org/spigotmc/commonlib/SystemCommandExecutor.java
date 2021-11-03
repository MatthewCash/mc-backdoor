package org.spigotmc.commonlib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCommandExecutor {
    public static String runSystemCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);

        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String output = "";
        String line = "";

        while ((line = reader.readLine()) != null) {
            output += line + "\n";
        }

        reader.close();

        return output;
    }
}
