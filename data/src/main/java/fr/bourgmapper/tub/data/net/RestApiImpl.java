package fr.bourgmapper.tub.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fr.bourgmapper.tub.data.entity.LineEntity;
import fr.bourgmapper.tub.data.entity.StopEntity;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {
    private ApiService apiService;

    /**
     * Constructor of the class
     */
    public RestApiImpl() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    @Override
    public Observable<List<LineEntity>> lineEntityList() {
        return apiService.getAllLines().map(new Function<LineListEnveloppe, List<LineEntity>>() {
            @Override
            public List<LineEntity> apply(LineListEnveloppe lineListEnveloppe) throws Exception {
                return lineListEnveloppe.lines;
            }
        });
    }

    @Override
    public Observable<LineEntity> lineEntityById(String lineId) {
        return apiService.getLine(lineId).map(new Function<LineEnveloppe, LineEntity>() {
            @Override
            public LineEntity apply(LineEnveloppe lineEnveloppe) {
                return lineEnveloppe.line;
            }
        });
    }

    @Override
    public Observable<List<StopEntity>> stopEntityList() {
        return apiService.getAllStops().map(new Function<StopListEnveloppe, List<StopEntity>>() {
            @Override
            public List<StopEntity> apply(StopListEnveloppe stopListEnveloppe) {
                return stopListEnveloppe.stops;
            }
        });
    }

    @Override
    public Observable<StopEntity> stopEntityById(String stopId) {
        return apiService.getStop(stopId).map(new Function<StopEnveloppe, StopEntity>() {
            @Override
            public StopEntity apply(StopEnveloppe stopEnveloppe) {
                return stopEnveloppe.stop;
            }
        });
    }


    private interface ApiService {
        @GET(API_URL_GET_LINE_LIST)
        Observable<LineListEnveloppe> getAllLines();

        @GET(API_URL_GET_LINE_DETAILS)
        Observable<LineEnveloppe> getLine(@Path("line_id") String line_id);

        @GET(API_URL_GET_STOP_LIST)
        Observable<StopListEnveloppe> getAllStops();

        @GET(API_URL_GET_STOP_DETAILS)
        Observable<StopEnveloppe> getStop(@Path("stop_id") String stop_id);
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
