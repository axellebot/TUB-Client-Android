package fr.bourgmapper.tub.presentation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import fr.bourgmapper.tub.data.entity.mapper.LineEntityDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopEntityDataMapper;
import fr.bourgmapper.tub.data.manager.DownloadManager;
import fr.bourgmapper.tub.data.manager.DownloadManagerImpl;

/**
 * Created by axell on 04/11/2016.
 */
public class AndroidApplication extends MultiDexApplication {
    private static AndroidApplication application;
    private DataRepository dataRepository;

    public static AndroidApplication app() {
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
        LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
        StopEntityDataMapper stopEntityDataMapper = new StopEntityDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, dbFlowManager, downloadManager, lineEntityDataMapper, stopEntityDataMapper);
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
