package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.ConfigFile;

public class FileValidationRunnable implements Runnable {
    private String filePath;

    private String threadName;

    private String processedDirectory;

    private String notProcessedDirectory;

    private File directoryOrigen;

    private ConfigFile configFileCreator;

    public FileValidationRunnable(File directory, String filePath, String threadName) {
        this.filePath = filePath;
        this.threadName = threadName;
        this.directoryOrigen = directory;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(threadName);
        Integer countNodes = Integer.valueOf(0);
        Set<String> nodes = new HashSet<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                index++;
                if (index == 1) {
                    if (!isHeaderLine(line)) {
                        System.out.println("Invalid Header");
                        moveToFailureDirectory();
                        return;
                    }

                    countNodes = Integer.valueOf(line.substring(2, 4));
                }

                Pattern pattern = Pattern.compile("[A-Za-z]\\d+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    nodes.add(matcher.group());
                }

            }
        } catch (IOException e) {
            // Tratamento de exceção, se necessário
            e.printStackTrace();
        }

        if(!countNodes.equals(nodes.size())){
            System.out.println("Error: Total number of invalid nodes");
            moveToFailureDirectory();
        }

        moveToSuccessDirectory();
    }

    private void moveToSuccessDirectory() {
        Path filePath = Path.of(this.filePath);
        try {
            Path successFilePath = Paths.get(directoryOrigen.toString(), ConfigFile.getSuccessDirectoryName(), filePath.getFileName().toString());
            System.out.println(filePath);
            Files.move(filePath, successFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved to processed directory: " + successFilePath.toAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Error moving file to failure directory: " + ex.getMessage());
        }
    }

    private void moveToFailureDirectory() {
        Path filePath = Path.of(this.filePath);
        try {
            Path failureFilePath = Paths.get(directoryOrigen.toString(), ConfigFile.getFailedDirectoryName(), filePath.getFileName().toString());
            Files.move(filePath, failureFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved to unprocessed directory: " + failureFilePath.toAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Error moving file to failure directory: " + ex.getMessage());
        }
    }

    private boolean isHeaderLine(String linha) {
        Pattern pattern = Pattern.compile("\\d{4};\\d{4};\\d{4}");
        Matcher matcher = pattern.matcher(linha);
        return matcher.matches();
    }

    private boolean isLastLine(String linha) {
        Pattern pattern = Pattern.compile("^09[A-Z]{2}=[0-9]+;[A-Z]{2}=[0-9]+;\\d{3};$");
        Matcher matcher = pattern.matcher(linha);
        return matcher.matches();
    }
}

