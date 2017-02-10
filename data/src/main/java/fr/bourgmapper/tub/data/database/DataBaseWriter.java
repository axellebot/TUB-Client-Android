package fr.bourgmapper.tub.data.database;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import fr.bourgmapper.tub.data.cache.DatabaseManager;

/**
 * {@link Runnable} class for evicting all the database data
 */
public class DataBaseWriter implements Runnable {

    private final DatabaseManager databaseManager;
    private final List<BaseModel> entityList;
    private final BaseModel entity;

    public DataBaseWriter(DatabaseManager databaseManager, BaseModel entity) {
        this.databaseManager = databaseManager;
        this.entity = entity;
        this.entityList = null;
    }

    public DataBaseWriter(DatabaseManager databaseManager, List<BaseModel> entityList) {
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