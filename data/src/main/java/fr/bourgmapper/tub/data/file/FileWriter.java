package fr.bourgmapper.tub.data.file;

import java.io.File;

import fr.bourgmapper.tub.data.file.FileManager;


/**
 * {@link Runnable} class for writing to disk.
 */
public class FileWriter implements Runnable {
    private final FileManager fileManager;
    private final File fileToWrite;
    private final String fileContent;

    public FileWriter(FileManager fileManager, File fileToWrite, String fileContent) {
        this.fileManager = fileManager;
        this.fileToWrite = fileToWrite;
        this.fileContent = fileContent;
    }

    @Override
    public void run() {
        this.fileManager.writeToFile(fileToWrite, fileContent);
    }
}