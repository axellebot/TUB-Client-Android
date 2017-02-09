package fr.bourgmapper.tub.data.manager;

import android.support.annotation.NonNull;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by axell on 04/11/2016.
 */

public class DatabaseManagerImpl implements DatabaseManager {

    private Realm realm() {
        return Realm.getDefaultInstance();
    }

    /***************************
     * SELECT
     **************************/

    @Override
    public Observable<RealmResults<LineEntity>> getLineEntityList() {
        return
                realm().where(LineEntity.class)
                        .findAll().asObservable();
    }

    @Override
    public Observable<LineEntity> getLineEntity(@NonNull String lineEntityId) {
        return
                realm().where(LineEntity.class)
                        .equalTo(LineEntity.KEY_ID, lineEntityId)
                        .findFirst().asObservable();

    }

    @Override
    public Observable<RealmResults<StopEntity>> getStopEntityList() {
        return
                realm().where(StopEntity.class)
                        .findAll().asObservable();
    }

    @Override
    public Observable<StopEntity> getStopEntity(@NonNull String stopEntityId) {
        return
                realm().where(StopEntity.class)
                        .equalTo(StopEntity.KEY_ID, stopEntityId)
                        .findFirst()
                        .asObservable();
    }

    /***************************
     * SAVE
     **************************/
    @Override
    public void saveLineEntityList(List<LineEntity> lineEntityList) {
        Realm realm = realm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(lineEntityList);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void saveLineEntity(LineEntity lineEntity) {
        Realm realm = realm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(lineEntity);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void saveStopEntityList(List<StopEntity> stopEntityList) {
        Realm realm = realm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(stopEntityList);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void saveStopEntity(StopEntity stopEntity) {
        Realm realm = realm();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(stopEntity);
        realm.commitTransaction();
        realm.close();
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
        Realm realm = realm();
        realm.beginTransaction();
        lineEntity.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void deleteStopEntityList(List<StopEntity> stopEntityList) {
        for (StopEntity stopEntity : stopEntityList) {
            deleteStopEntity(stopEntity);
        }
    }

    @Override
    public void deleteStopEntity(StopEntity stopEntity) {
        Realm realm = realm();
        realm.beginTransaction();
        stopEntity.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }
}
