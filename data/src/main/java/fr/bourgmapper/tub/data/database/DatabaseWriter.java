package fr.bourgmapper.tub.data.database;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;


/**
 * {@link Runnable} class for evicting all the database data
 */
public class DatabaseWriter implements Runnable {

    private final DatabaseManager databaseManager;
    private final List<BaseModel> entityList;
    private final BaseModel entity;

    public DatabaseWriter(DatabaseManager databaseManager, BaseModel entity) {
        this.databaseManager = databaseManager;
        this.entity = entity;
        this.entityList = null;
    }

    public DatabaseWriter(DatabaseManager databaseManager, List<BaseModel> entityList) {
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
