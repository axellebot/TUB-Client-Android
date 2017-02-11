package fr.bourgmapper.tub.data.database;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;

/**
 * Helper class to do operations on database.
 */
@Singleton
public class DatabaseManager {

    @Inject
    DatabaseManager() {
    }

    /***************************
     * SELECT
     **************************/
    public List<LineEntity> getLineEntityList() {
        return
                SQLite.select()
                        .from(LineEntity.class)
                        .queryList();
    }

    public LineEntity getLineEntityById(String id) {
        Condition columnId = Condition.column(NameAlias.builder("stopId").build());
        return
                SQLite.select()
                        .from(LineEntity.class)
                        .where(columnId.is(id))
                        .querySingle();
    }

    public List<StopEntity> getStopEntityList() {
        return
                SQLite.select()
                        .from(StopEntity.class)
                        .queryList();
    }

    public StopEntity getStopEntityById(String id) {
        Condition columnId = Condition.column(NameAlias.builder("stopId").build());
        return
                SQLite.select()
                        .from(StopEntity.class)
                        .where(columnId.is(id))
                        .querySingle();
    }

    /***************************
     * SAVE
     **************************/
    public void saveEntityList(@NonNull List<BaseModel> entityList) {
        for (BaseModel entity : entityList) {
            saveEntity(entity);
        }
    }

    public void saveEntity(@NonNull BaseModel entity) {
        entity.save();
    }

    /***************************
     * DELETE
     **************************/
    public void deleteEntityList(@NonNull List<BaseModel> entityList) {
        for (BaseModel entity : entityList) {
            deleteEntity(entity);
        }
    }

    public void deleteEntity(@NonNull BaseModel baseModel) {
        baseModel.delete();
    }

    public void clearTable() {
        //TODO: Add Clear Table
    }

    public boolean lineEntityExists(String id) {
        return getLineEntityById(id).exists();
    }

    public boolean stopEntityExists(String id) {
        return getStopEntityById(id).exists();
    }
}
