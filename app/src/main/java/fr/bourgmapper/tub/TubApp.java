package fr.bourgmapper.tub;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import fr.bourgmapper.tub.data.entity.mapper.LineDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopDataMapper;
import fr.bourgmapper.tub.data.manager.ApiManager;
import fr.bourgmapper.tub.data.manager.ApiManagerImpl;
import fr.bourgmapper.tub.data.manager.DatabaseManager;
import fr.bourgmapper.tub.data.manager.DatabaseManagerImpl;
import fr.bourgmapper.tub.data.manager.DownloadManager;
import fr.bourgmapper.tub.data.manager.DownloadManagerImpl;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.data.repository.DataRepositoryImpl;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by axell on 04/11/2016.
 */
public class TubApp extends MultiDexApplication {
    private static TubApp application;
    private DataRepository dataRepository;

    public static TubApp app() {
        return application;
    }

    @Override
    public void onCreate() {
        application = this;
        initDatabase();
        initInjection();
    }

    private void initDatabase() {
        Realm.init(application);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void initInjection() {
        ApiManager apiManager = new ApiManagerImpl();
        DatabaseManager databaseManager = new DatabaseManagerImpl();
        DownloadManager downloadManager = new DownloadManagerImpl();
        LineDataMapper lineDataMapper = new LineDataMapper();
        StopDataMapper stopDataMapper = new StopDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, databaseManager, downloadManager, lineDataMapper, stopDataMapper);
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
