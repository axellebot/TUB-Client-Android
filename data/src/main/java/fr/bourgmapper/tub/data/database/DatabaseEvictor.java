package fr.bourgmapper.tub.data.database;


/**
 * {@link Runnable} class for evicting all the database data
 */
public class DatabaseEvictor implements Runnable {
    private final DatabaseManager databaseManager;
    private final Class<?> table;

    public DatabaseEvictor(DatabaseManager databaseManager, Class<?> table) {
        this.databaseManager = databaseManager;
        this.table = table;
    }

    @Override
    public void run() {
        this.databaseManager.clearTable(this.table);
    }
}