package fr.bourgmapper.tub.data.database;

import fr.bourgmapper.tub.data.cache.DatabaseManager;

/**
 * {@link Runnable} class for evicting all the database data
 */
public class DatabaseEvictor implements Runnable {
    private final DatabaseManager databaseManager;

    public DatabaseEvictor(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void run() {
        this.databaseManager.clearTable();
    }
}