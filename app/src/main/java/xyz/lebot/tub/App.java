package xyz.lebot.tub;

import android.app.Application;

import xyz.lebot.tub.data.entity.mapper.LineDataMapper;
import xyz.lebot.tub.data.entity.mapper.StopDataMapper;
import xyz.lebot.tub.data.manager.ApiManager;
import xyz.lebot.tub.data.manager.ApiManagerImpl;
import xyz.lebot.tub.data.manager.CacheManager;
import xyz.lebot.tub.data.manager.CacheManagerImpl;
import xyz.lebot.tub.data.manager.DownloadManager;
import xyz.lebot.tub.data.manager.DownloadManagerImpl;
import xyz.lebot.tub.data.manager.UserManager;
import xyz.lebot.tub.data.manager.UserManagerImpl;
import xyz.lebot.tub.data.repository.DataRepository;
import xyz.lebot.tub.data.repository.DataRepositoryImpl;

/**
 * Created by axell on 04/11/2016.
 */
public class App extends Application {
    private static App ourInstance;

    private DataRepository dataRepository;


    public App() {
        ourInstance = this;
        injectDependencies();
    }

    public static App getInstance() {
        return ourInstance;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    private void injectDependencies() {
        ApiManager apiManager = new ApiManagerImpl();
        CacheManager cacheManager = new CacheManagerImpl();
        DownloadManager downloadManager = new DownloadManagerImpl();
        UserManager userManager = new UserManagerImpl(this);
        LineDataMapper lineDataMapper = new LineDataMapper();
        StopDataMapper stopDataMapper = new StopDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, cacheManager, downloadManager, userManager, lineDataMapper, stopDataMapper);
    }


}
