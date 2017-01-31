package fr.bourgmapper.tub.data.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by axell on 12/11/2016.
 */

public class DownloadManagerImpl implements DownloadManager {

    private  DownloadService downloadService;

    public DownloadManagerImpl() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DOWNLOAD_DEV_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        downloadService = retrofit.create(DownloadService.class);
    }
    @Override
    public Observable<ResponseBody> getLineKmlFile(String id) {
        return downloadService.getLineKMLFile(id);
    }

    private interface DownloadService {
        @GET("kml/{line}")
        Observable<ResponseBody> getLineKMLFile(@Path("line") String id);
    }
}
