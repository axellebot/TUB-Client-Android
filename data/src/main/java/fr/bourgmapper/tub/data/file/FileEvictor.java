package fr.bourgmapper.tub.data.file;

import java.io.File;

import fr.bourgmapper.tub.data.file.FileManager;

/**
 * {@link Runnable} class for evicting all the cached files
 */
public class FileEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    public FileEvictor(FileManager fileManager, File cacheDir) {
        this.fileManager = fileManager;
        this.cacheDir = cacheDir;
    }

    @Override
    public void run() {
        this.fileManager.clearDirectory(this.cacheDir);
    }
}