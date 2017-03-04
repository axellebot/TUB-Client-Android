package fr.bourgmapper.tub.data.database;

import android.content.Context;
import android.support.annotation.NonNull;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.DaoMaster;
import fr.bourgmapper.tub.data.entity.DaoMaster.DevOpenHelper;
import fr.bourgmapper.tub.data.entity.DaoSession;

/**
 * Helper class to do operations on database.
 */
@Singleton
public class DatabaseManager {
    private DaoSession daoSession;

    @Inject
    DatabaseManager(Context context) {
        DevOpenHelper helper = new DevOpenHelper(context, "tub-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
    }

    /***************************
     * SELECT
     **************************/

    public <T> List<?> getEntityList(Class<T> entityClass) {
        return this.getDao(entityClass).loadAll();
    }

    public <T> Object getEntityById(Class<T> entityClass, int id) {
        return this.getDao(entityClass).loadByRowId(id);
    }

    /***************************
     * SAVE
     **************************/
    public void saveEntityList(@NonNull List<Object> entityList) {
        for (Object entity : entityList) {
            this.saveEntity(entity);
        }
    }

    public  <?> void saveEntity(<? extends Object> entity) {
        getDao(entity. getClass()).save(entity);
    }

    /***************************
     * DELETE
     **************************/
    public void deleteEntityList(@NonNull List<Object> entityList) {
        for (Object entity : entityList) {
            this.deleteEntity(entity);
        }
    }

    public void deleteEntity(@NonNull Object entity) {
        this.getDao(entity.getClass()).delete(entity);
    }

    /**
     * Deletes the specified table
     *
     * @param entityClass The entity to delete
     * @param <T>         The class that implements
     */
    public <T> void clearTable(Class<T> entityClass) {
        .thisgetDao(entityClass).deleteAll();
    }


    private AbstractDao<?, ?> getDao(Class<? extends Object> entityClass) {
        return daoSession.getDao(entityClass);
    }
}
