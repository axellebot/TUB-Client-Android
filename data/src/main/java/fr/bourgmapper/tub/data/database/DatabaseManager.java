package fr.bourgmapper.tub.data.database;

import android.content.Context;
import android.support.annotation.NonNull;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.bourgmapper.tub.data.entity.DaoMaster;
import fr.bourgmapper.tub.data.entity.DaoMaster.DevOpenHelper;
import fr.bourgmapper.tub.data.entity.DaoSession;
import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;

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
        this.daoSession = new DaoMaster(db).newSession();
    }

    /***************************
     * SELECT
     **************************/

    public <T> List<T> getEntityList(Class<T> entityClass) {
        return (List<T>) this.getDao(entityClass).loadAll();
    }

    public <T> T getEntityById(Class<T> entityClass, long id) {
        return (T) this.getDao(entityClass).loadByRowId(id);
    }

    /***************************
     * SAVE
     **************************/
    public void saveEntityList(@NonNull List<Object> entityList) {
        for (Object entity : entityList) {
            this.saveEntity(entity);
        }
    }

    public void saveEntity(@NonNull Object entity) {
        if (entity instanceof LineEntity) {
            this.daoSession.getLineEntityDao().delete((LineEntity) entity);
        } else if (entity instanceof StopEntity) {
            this.daoSession.getStopEntityDao().delete((StopEntity) entity);
        }
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
        if (entity instanceof LineEntity) {
            this.daoSession.getLineEntityDao().delete((LineEntity) entity);
        } else if (entity instanceof StopEntity) {
            this.daoSession.getStopEntityDao().delete((StopEntity) entity);
        }
    }

    /***************************
     * EXIST
     **************************/

    public <T> boolean exist(T entity, long id) {
        WhereCondition condition = getDao(entity.getClass()).getPkProperty().eq(id); //TODO : find better condition with ID
        return this.getDao(entity.getClass()).queryBuilder().where(condition).unique() != null;
    }

    /**
     * Deletes the specified table
     *
     * @param entityClass The entity to delete
     * @param <T>         The class that implements
     */
    public <T> void clearTable(Class<T> entityClass) {
        this.getDao(entityClass).deleteAll();
    }


    private AbstractDao<?, ?> getDao(Class<? extends Object> entityClass) {
        return daoSession.getDao(entityClass);
    }
}
