package fr.bourgmapper.tub.data.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
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
    public Observable<LineEntity> getLine(String line_id) {
        return apiService.getLine(line_id).map(new Func1<LineEnveloppe, LineEntity>() {
            @Override
            public LineEntity call(LineEnveloppe lineEnveloppe) {
                return lineEnveloppe.line;
            }
        });
    }

    @Override
    public Observable<List<LineEntity>> getLinesFromStop(String stop_id) {
        return apiService.getLinesFromStop(stop_id).map(new Func1<LineListEnveloppe, List<LineEntity>>() {
            @Override
            public List<LineEntity> call(LineListEnveloppe lineEnveloppe) {
                return lineEnveloppe.lines;
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
    public Observable<StopEntity> getStop(String stop_id) {
        return apiService.getStop(stop_id).map(new Func1<StopEnveloppe, StopEntity>() {
            @Override
            public StopEntity call(StopEnveloppe stopEnveloppe) {
                return stopEnveloppe.stop;
            }
        });
    }

    @Override
    public Observable<List<StopEntity>> getStopsFromLine(String line_id) {
        return apiService.getStopsFromLine(line_id).map(new Func1<StopListEnveloppe, List<StopEntity>>() {
            @Override
            public List<StopEntity> call(StopListEnveloppe stopListEnveloppe) {
                return stopListEnveloppe.stops;
            }
        });
    }

    private interface ApiService {
        @GET("lines")
        Observable<LineListEnveloppe> getAllLines();

        @GET("lines/{line_id}")
        Observable<LineEnveloppe> getLine(@Path("line_id") String line_id);

        @GET("lines/{line_id}/stops")
        Observable<StopListEnveloppe> getStopsFromLine(@Path("line_id") String line_id);

        @GET("stops")
        Observable<StopListEnveloppe> getAllStops();

        @GET("stops/{stop_id}")
        Observable<StopEnveloppe> getStop(@Path("stop_id") String stop_id);

        @GET("stops/{stop_id}/lines")
        Observable<LineListEnveloppe> getLinesFromStop(@Path("stop_id") String stop_id);
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
