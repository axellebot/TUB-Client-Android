package fr.bourgmapper.tub;

import android.support.multidex.MultiDexApplication;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import fr.bourgmapper.tub.data.entity.mapper.LineDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopDataMapper;
import fr.bourgmapper.tub.data.manager.ApiManager;
import fr.bourgmapper.tub.data.manager.ApiManagerImpl;
import fr.bourgmapper.tub.data.manager.CacheManager;
import fr.bourgmapper.tub.data.manager.CacheManagerImpl;
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
        CacheManager cacheManager = new CacheManagerImpl();
        DownloadManager downloadManager = new DownloadManagerImpl();
        LineDataMapper lineDataMapper = new LineDataMapper();
        StopDataMapper stopDataMapper = new StopDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, cacheManager, downloadManager, lineDataMapper, stopDataMapper);
    }

    private void initDBFlow() {
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }
}
