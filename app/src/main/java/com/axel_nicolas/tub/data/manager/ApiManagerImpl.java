package com.axel_nicolas.tub.data.manager;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.axel_nicolas.tub.data.entity.StopEntity;
import com.axel_nicolas.tub.data.model.LineModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by axell on 04/11/2016.
 */

public class ApiManagerImpl implements ApiManager {

    private ApiService apiService;

    private interface ApiService {
        @GET("lines")
        Observable<LineListEnveloppe> getAllLines();

        @GET("lines/{line}")
        Observable<LineEnveloppe> getLine(@Path("line") String id);

        @GET("stops")
        Observable<StopListEnveloppe> getAllStops();

        @GET("stops/{stop}")
        Observable<StopEnveloppe> getStop(@Path("stop") String id);
    }

    public ApiManagerImpl() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_PROD_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<List<LineEntity>> getAllLines() {
        return apiService.getAllLines().map(new Func1<LineListEnveloppe, List<LineEntity>>() {
            @Override
            public List<LineEntity> call(LineListEnveloppe lineListEnveloppe) {
                return lineListEnveloppe.lines;
            }
        });
    }

    @Override
    public Observable<LineEntity> getLine(String id) {
        return apiService.getLine(id).map(new Func1<LineEnveloppe, LineEntity>() {
            @Override
            public LineEntity call(LineEnveloppe lineEnveloppe) {
                return lineEnveloppe.line;
            }
        });
    }


    @Override
    public Observable<List<StopEntity>> getAllStops() {
        return apiService.getAllStops().map(new Func1<StopListEnveloppe, List<StopEntity>>() {
            @Override
            public List<StopEntity> call(StopListEnveloppe stopListEnveloppe) {
                return stopListEnveloppe.stops;
            }
        });
    }

    @Override
    public Observable<StopEntity> getStop(String id) {
        return apiService.getStop(id).map(new Func1<StopEnveloppe, StopEntity>() {
            @Override
            public StopEntity call(StopEnveloppe stopEnveloppe) {
                return stopEnveloppe.stop;
            }
        });
    }

    private class LineListEnveloppe {
        List<LineEntity> lines;
    }

    private class LineEnveloppe {
        LineEntity line;
    }

    private class StopListEnveloppe {
        List<StopEntity> stops;
    }

    private class StopEnveloppe {
        StopEntity stop;
    }
}
