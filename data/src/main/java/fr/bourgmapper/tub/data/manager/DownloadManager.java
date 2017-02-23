package fr.bourgmapper.tub.data.manager;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by axell on 12/11/2016.
 */

public interface DownloadManager {
    String DOWNLOAD_PROD_URL = "https://tub.bourgmapper.fr/download/";
    String DOWNLOAD_DEV_URL = "https://dev.tub.bourgmapper.fr/download/";

    Observable<ResponseBody> getLineKmlFile(String id);
}
