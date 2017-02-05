package fr.bourgmapper.tub.data.manager;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.LineEntity_Table;
import fr.bourgmapper.tub.data.entity.StopEntity;
import fr.bourgmapper.tub.data.entity.StopEntity_Table;

/**
 * Created by axell on 04/11/2016.
 */

public class DBFlowManagerImpl implements DBFlowManager {
    /***************************
     * SELECT
     **************************/

    @Override
    public List<LineEntity> getLineEntityList() {
        return
                SQLite.select().
                        from(LineEntity.class).
                        queryList();
    }

    @Override
    public LineEntity getLineEntity(@NonNull String lineEntityId) {
        return
                SQLite.select().
                        from(LineEntity.class).
                        where(LineEntity_Table.id.is(lineEntityId)).
                        querySingle();
    }

    @Override
    public List<StopEntity> getStopEntityList() {
        return
                SQLite.select().
                        from(StopEntity.class).
                        queryList();
    }

    @Override
    public StopEntity getStopEntity(@NonNull String stopEntityId) {
        return
                SQLite.select()
                        .from(StopEntity.class)
                        .where(StopEntity_Table.id.is(stopEntityId))
                        .querySingle();
    }

    /***************************
     * SAVE
     **************************/
    @Override
    public void saveLineEntityList(List<LineEntity> lineEntityList) {
        for (LineEntity lineEntity : lineEntityList) {
            saveLineEntity(lineEntity);
        }
    }

    @Override
    public void saveLineEntity(LineEntity lineEntity) {
        lineEntity.save();
    }

    @Override
    public void saveStopEntityList(List<StopEntity> stopEntityList) {
        for (StopEntity stopEntity : stopEntityList) {
            saveStopEntity(stopEntity);
        }
    }

    @Override
    public void saveStopEntity(StopEntity stopEntity) {
        stopEntity.save();
    }

    /***************************
     * DELETE
     **************************/
    @Override
    public void deleteLineEntityList(List<LineEntity> lineEntityList) {
        for (LineEntity lineEntity : lineEntityList) {
            deleteLineEntity(lineEntity);
        }
    }

    @Override
    public void deleteLineEntity(LineEntity lineEntity) {
        lineEntity.delete();
    }

    @Override
    public void deleteStopEntityList(List<StopEntity> stopEntityList) {
        for (StopEntity stopEntity : stopEntityList) {
            deleteStopEntity(stopEntity);
        }
    }

    @Override
    public void deleteStopEntity(StopEntity stopEntity) {
        stopEntity.delete();
    }
}
