package config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ConfigFile {

    private static String destinationPath;
    private static String testPath;
    private static String configFilePath;
    private static String successDirectoryName;
    private static String failedDirectoryName;

    public void createConfigFile(){
        destinationPath = System.getProperty("user.dir");
        successDirectoryName = "processed";
        failedDirectoryName = "unprocessed";
        createFile();
    }

    public void createConfigFile(String destinationPath, String successDirectoryName, String failedDirectoryName){
        ConfigFile.destinationPath = destinationPath;
        ConfigFile.successDirectoryName = successDirectoryName;
        ConfigFile.failedDirectoryName = failedDirectoryName;
        createFile();
    }

    public static String getDestinationPath() {
        return destinationPath;
    }

    public static String getSuccessDirectoryName() {
        return successDirectoryName;
    }

    public static String getFailedDirectoryName() {
        return failedDirectoryName;
    }

    public static String getConfigFilePath() {return destinationPath + "/test/configuration/config.txt";};

    public static String getTestPath() {return destinationPath + "/test";};

    private void createFile() {
        String diretorioConfiguration = Paths.get(destinationPath)  + "/test/configuration";
        String diretorioTest = destinationPath + "/test";
        String arquivoConfig = "config.txt";
        String absolutePath = "";

        try {
            if (!new File(diretorioConfiguration).exists()) {
                Files.createDirectories(Path.of(diretorioConfiguration));

                Path arquivoConfigPath = Path.of(diretorioConfiguration, arquivoConfig);
                StringBuilder conteudoArquivo = new StringBuilder();
                conteudoArquivo
                        .append(successDirectoryName + "=")
                        .append(diretorioTest)
                        .append("/" + successDirectoryName + "\n")
                        .append(failedDirectoryName + "=")
                        .append(diretorioTest)
                        .append("/" + failedDirectoryName + "\n");

                absolutePath = arquivoConfigPath.toAbsolutePath().toString();
                
                System.out.println("conteudoArquivo: " + conteudoArquivo.toString());

                Files.write(arquivoConfigPath, conteudoArquivo.toString().getBytes(), StandardOpenOption.CREATE);
                System.out.println("Configuration file created: " + arquivoConfigPath.toAbsolutePath());
            }
        } catch (IOException ex) {
            System.out.println("Error creating directories or writing configuration file: " + ex.getMessage());
        }
    }
}

