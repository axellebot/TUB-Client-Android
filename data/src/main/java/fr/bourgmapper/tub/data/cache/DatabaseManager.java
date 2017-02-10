package fr.bourgmapper.tub.data.cache;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

//TODO : Create actions in series like SQLITE

public class DatabaseManager {
    /***************************
     * SELECT
     **************************/
    public List<BaseModel> getEntityList(@NonNull String table) {
        return
                SQLite.select()
                        .from(table)
                        .queryList();
    }

    public BaseModel getEntityById(@NonNull String id, @NonNull String table) {
        Condition columnId = Condition.column(NameAlias.builder("id").build());
        return
                SQLite.select()
                        .from(table)
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

    public boolean exists(@NonNull String id, @NonNull Class<Object> objectClass) {
        BaseModel baseModel = (BaseModel) getEntityById(id, objectClass);
        return baseModel.exists();
    }

    public Object getClassFromClassName(String entityClassName) {

        Class<BaseModel> entityClass = null;

        try {
            entityClass = (Class<BaseModel>) Class.forName(entityClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return entityClass;
    }
}
