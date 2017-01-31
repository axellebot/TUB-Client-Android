package fr.bourgmapper.tub;

import android.app.Application;

import fr.bourgmapper.tub.data.entity.mapper.LineDataMapper;
import fr.bourgmapper.tub.data.entity.mapper.StopDataMapper;
import fr.bourgmapper.tub.data.manager.ApiManager;
import fr.bourgmapper.tub.data.manager.CacheManager;
import fr.bourgmapper.tub.data.manager.CacheManagerImpl;
import fr.bourgmapper.tub.data.manager.ApiManagerImpl;
import fr.bourgmapper.tub.data.manager.DownloadManager;
import fr.bourgmapper.tub.data.manager.DownloadManagerImpl;
import fr.bourgmapper.tub.data.repository.DataRepository;
import fr.bourgmapper.tub.data.repository.DataRepositoryImpl;

/**
 * Created by axell on 04/11/2016.
 */
public class TubApp extends Application {
    private static TubApp ourInstance;

    private DataRepository dataRepository;


    public TubApp() {
        ourInstance = this;
        injectDependencies();
    }

    public static TubApp getInstance() {
        return ourInstance;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    private void injectDependencies() {
        ApiManager apiManager = new ApiManagerImpl();
        CacheManager cacheManager = new CacheManagerImpl();
        DownloadManager downloadManager = new DownloadManagerImpl();
        LineDataMapper lineDataMapper = new LineDataMapper();
        StopDataMapper stopDataMapper = new StopDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, cacheManager, downloadManager, lineDataMapper, stopDataMapper);
    }


}
