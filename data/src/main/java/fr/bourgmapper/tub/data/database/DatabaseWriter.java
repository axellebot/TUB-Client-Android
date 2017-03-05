package fr.bourgmapper.tub.data.database;

import java.util.List;


/**
 * {@link Runnable} class for evicting all the database data
 */
public class DatabaseWriter implements Runnable {

    private final DatabaseManager databaseManager;
    private final List<Object> entityList;
    private final Object entity;

    public DatabaseWriter(DatabaseManager databaseManager, Object entity) {
        this.databaseManager = databaseManager;
        this.entity = entity;
        this.entityList = null;
    }

    public DatabaseWriter(DatabaseManager databaseManager, List<Object> entityList) {
        this.databaseManager = databaseManager;
        this.entity = null;
        this.entityList = entityList;
    }

    @Override
    public void run() {
        if (this.entity != null) {
            this.databaseManager.saveEntity(this.entity);
        } else if (this.entityList != null) {
            this.databaseManager.saveEntityList(this.entityList);
        }
    }
}
