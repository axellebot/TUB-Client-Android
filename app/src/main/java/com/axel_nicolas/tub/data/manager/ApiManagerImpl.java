package com.axel_nicolas.tub.data.manager;

import com.axel_nicolas.tub.data.entity.LineEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by axell on 04/11/2016.
 */

public class ApiManagerImpl implements ApiManager {

    private ApiService apiService;

    private interface ApiService{
        @GET("lines")
        Observable<LineListEnveloppe> getAllLines();
    }

    public ApiManagerImpl(){
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

    private class LineListEnveloppe{
        List<LineEntity> lines;
    }
    private class LineEnveloppe{
        LineEntity line;
    }
}
