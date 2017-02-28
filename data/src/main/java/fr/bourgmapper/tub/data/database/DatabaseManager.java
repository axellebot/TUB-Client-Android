package fr.bourgmapper.tub.data.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.BuildConfig;
import fr.bourgmapper.tub.data.entity.CacheDatabase;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.domain.Line;

/**
 * Helper class to do operations on database.
 */
@Singleton
public class DatabaseManager {

    @Inject
    DatabaseManager(Context context) {
    }

    /***************************
     * SELECT
     **************************/

    public <TModel> TModel getEntityById(Class<TModel> table, String id) {
        Condition columnId = Condition.column(NameAlias.builder("id").build());
        return
                SQLite.select()
                        .from(table)
                        .where(columnId.is(id))
                        .querySingle();
    }

    public <TModel> List<TModel> getEntityList(Class<TModel> table) {
        return
                SQLite.select()
                        .from(table)
                        .queryList();
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
            this.deleteEntity(entity);
        }
    }

    public void deleteEntity(@NonNull BaseModel baseEntity) {
        baseEntity.delete();
    }

    /**
     * Deletes the specified table
     *
     * @param table    The table to delete
     * @param <TModel> The class that implements {@link BaseModel}
     */
    public <TModel> void clearTable(Class<TModel> table) {
        Delete.table(table);
    }

    /**
     * @param table
     * @param id
     * @return
     */
    public <TModel> boolean entityExists(Class<TModel> table, String id) {
        BaseModel baseEntity = (BaseModel) getEntityById(table, id);
        return baseEntity.exists();
    }
}
