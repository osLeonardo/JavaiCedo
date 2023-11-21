package files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import config.ConfigFile;
import config.ConfigFileValidator;

public class FileProcessor {
    public void readConfigFile (String configFile) {

        ConfigFileValidator.validateConfigFile(ConfigFile.getConfigFilePath());

        try {
            List<String> directories = Files.lines(Paths.get(ConfigFile.getConfigFilePath()))
                    .map(line -> line.replaceAll(".*=", ""))
                    .collect(Collectors.toList());

            createDirectories(directories);

        } catch (Exception ex){
            System.out.println("Error reading directories");
        }
    }
    private void createDirectories(List<String> directories){
        directories.forEach(line -> {
            try {
                if(!new File(line).exists()){
                    Files.createDirectories(Path.of(line));
                }
            } catch (IOException e) {
                System.out.println("Error creating directories");
            }
        });
    }

    public void processFiles() {
        FileProcessorPoolManager fileProcessorPoolManager = new FileProcessorPoolManager();

        try {
            String newFileName = generateSequentialFileName();
            System.out.println(ConfigFile.getTestPath());
            fileProcessorPoolManager.processFilesInDirectory(ConfigFile.getTestPath());
        } catch (IOException ex) {
            System.out.println("Error processing files: " + ex.getMessage());
        }
    }

    private String generateSequentialFileName() throws IOException {
        Integer sequenceNumber = 0;

        String sequentialFileName = "route" + String.format("%02d", sequenceNumber) + ".txt";

        while (Files.exists(Paths.get(ConfigFile.getTestPath(), sequentialFileName))) {
            sequenceNumber++;
            sequentialFileName = "route" + String.format("%02d", sequenceNumber) + ".txt";
        }

        return sequentialFileName;
    }


    private Path createFile(String fileName) throws IOException {
        Path filePath = Paths.get(ConfigFile.getTestPath(), fileName);

        Files.createFile(filePath);
        System.out.println("File created: " + filePath.toAbsolutePath());

        return filePath;
    }
}

