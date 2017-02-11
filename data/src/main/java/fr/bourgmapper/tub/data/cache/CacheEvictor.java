package fr.bourgmapper.tub.data.cache;

import java.io.File;

import fr.bourgmapper.tub.data.file.FileManager;

/**
 * {@link Runnable} class for evicting all the cached files
 */
public class CacheEvictor implements Runnable {
    private final FileManager fileManager;
    private final File cacheDir;

    public CacheEvictor(FileManager fileManager, File cacheDir) {
        this.fileManager = fileManager;
        this.cacheDir = cacheDir;
    }

    @Override
    public void run() {
        this.fileManager.clearDirectory(this.cacheDir);
    }
}