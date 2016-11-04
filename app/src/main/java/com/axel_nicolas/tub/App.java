package com.axel_nicolas.tub;

import android.app.Application;

import com.axel_nicolas.tub.data.entity.mapper.LineDataMapper;
import com.axel_nicolas.tub.data.manager.ApiManager;
import com.axel_nicolas.tub.data.manager.ApiManagerImpl;
import com.axel_nicolas.tub.data.manager.CacheManager;
import com.axel_nicolas.tub.data.manager.CacheManagerImpl;
import com.axel_nicolas.tub.data.repository.DataRepository;
import com.axel_nicolas.tub.data.repository.DataRepositoryImpl;

/**
 * Created by axell on 04/11/2016.
 */
public class App extends Application {
    private static App ourInstance;

    private DataRepository dataRepository;


    public static App getInstance() {
        return ourInstance;
    }

    public App() {
        ourInstance = this;
        injectDependencies();
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    private void injectDependencies() {
        ApiManager apiManager = new ApiManagerImpl();
        CacheManager cacheManager = new CacheManagerImpl();
        LineDataMapper lineDataMapper = new LineDataMapper();

        dataRepository = new DataRepositoryImpl(apiManager, cacheManager, lineDataMapper);
    }


}
