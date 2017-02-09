package fr.bourgmapper.tub.data.manager;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public interface DatabaseManager {
    /***************************
     * SELECT
     **************************/
    Observable<RealmResults<LineEntity>> getLineEntityList();

    Observable<LineEntity> getLineEntity(String lineEntityId);

    Observable<RealmResults<StopEntity>> getStopEntityList();

    Observable<StopEntity> getStopEntity(String stopEntityId);

    /***************************
     * SAVE
     **************************/
    void saveLineEntityList(List<LineEntity> lineEntityList);

    void saveLineEntity(LineEntity lineEntity);

    void saveStopEntityList(List<StopEntity> stopEntityList);

    void saveStopEntity(StopEntity stopEntity);

    /***************************
     * DELETE
     **************************/
    void deleteLineEntityList(List<LineEntity> lineEntityList);

    void deleteLineEntity(LineEntity lineEntity);

    void deleteStopEntityList(List<StopEntity> stopEntityList);

    void deleteStopEntity(StopEntity stopEntity);
}

