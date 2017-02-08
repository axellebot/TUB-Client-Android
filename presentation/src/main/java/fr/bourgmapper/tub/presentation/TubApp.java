package fr.bourgmapper.tub.presentation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import fr.bourgmapper.tub.data.entity.mapper.LineDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopDataMapper;
import fr.bourgmapper.tub.data.manager.ApiManager;
import fr.bourgmapper.tub.data.manager.ApiManagerImpl;
import fr.bourgmapper.tub.data.manager.DBFlowManager;
import fr.bourgmapper.tub.data.manager.DBFlowManagerImpl;
import fr.bourgmapper.tub.data.manager.DownloadManager;
import fr.bourgmapper.tub.data.manager.DownloadManagerImpl;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.data.repository.DataRepositoryImpl;

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
        initInjection();
        initDBFlow();
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    private void initInjection() {
        ApiManager apiManager = new ApiManagerImpl();
        DBFlowManager dbFlowManager = new DBFlowManagerImpl();
        DownloadManager downloadManager = new DownloadManagerImpl();
        LineDataMapper lineDataMapper = new LineDataMapper();
        StopDataMapper stopDataMapper = new StopDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, dbFlowManager, downloadManager, lineDataMapper, stopDataMapper);
    }

    private void initDBFlow() {
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this)
                .addDatabaseHolder(SomeUniqueModuleNameGeneratedDatabaseHolder.class)
                .build());
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
