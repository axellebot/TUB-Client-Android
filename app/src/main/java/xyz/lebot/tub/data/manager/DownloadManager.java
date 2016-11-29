package xyz.lebot.tub.data.manager;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by axell on 12/11/2016.
 */

public interface DownloadManager {
    String DOWNLOAD_PROD_URL = "https://tub.bourgmapper.fr/download/";
    String DOWNLOAD_DEV_URL = "https://dev.tub.bourgmapper.fr/download/";

    Observable<ResponseBody> getLineKmlFile(String id);
}
