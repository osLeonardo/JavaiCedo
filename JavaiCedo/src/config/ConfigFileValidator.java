package config;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConfigFileValidator {
    public static void validateConfigFile(String configFilePath) {
        validateConfigFileExistence(configFilePath);
        validateConfigFileNotEmpty(configFilePath);
        validateConfigFileContent(configFilePath);
    }

    private static void validateConfigFileExistence(String configFilePath) {
        File configFile = new File(configFilePath);

        if (!configFile.exists()) {
            JOptionPane.showMessageDialog(null, "Config file does not exist: " + configFilePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void validateConfigFileNotEmpty(String configFilePath) {
        File configFile = new File(configFilePath);

        if (configFile.length() == 0) {
            JOptionPane.showMessageDialog(null, "Config file is empty: " + configFilePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void validateConfigFileContent(String configFilePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configFilePath));

            for (String line : lines) {
                if (line.contains("@")) {
                    JOptionPane.showMessageDialog(null, "Config file is invalid: " + configFilePath, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading config file: " + configFilePath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
