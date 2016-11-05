package com.axel_nicolas.tub.ui.presenter;

import android.util.Log;

import com.axel_nicolas.tub.App;
import com.axel_nicolas.tub.data.model.LineModel;
import com.axel_nicolas.tub.data.model.StopModel;
import com.axel_nicolas.tub.ui.fragment.LineFragment;
import com.axel_nicolas.tub.ui.fragment.StopFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by axell on 05/11/2016.
 */

public class StopFragmentPresenter {
    private static String TAG = "StopFragmentPresenter";

    private final StopFragment view;

    public StopFragmentPresenter(final StopFragment view) {
        this.view = view;


        App.getInstance().getDataRepository().getAllStopsCall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<StopModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<StopModel> stopModels) {
                        Log.i(TAG, stopModels.toString());
                        view.initList(stopModels);
                    }
                });
    }

}
