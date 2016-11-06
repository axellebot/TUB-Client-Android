package com.axel_nicolas.tub.ui.presenter;

import com.axel_nicolas.tub.R;
import com.axel_nicolas.tub.ui.activity.MainActivity;

/**
 * Created by axell on 06/11/2016.
 */

public class MainActivityPresenter implements Presenter {

    private static String TAG = "MapFragmentPresenter";

    private final MainActivity view;

    public MainActivityPresenter(final MainActivity view) {
        this.view = view;
    }


    public void onBottomNavigationClick(int position) {
        switch (position) {
            case 0:
                view.setTitle(view.getResources().getString(R.string.navigation_part_line_name));
                view.setCurrentItem(0);
                break;
            case 1:
                view.setTitle(view.getResources().getString(R.string.navigation_part_stop_name));
                view.setCurrentItem(1);
                break;
            case 2:
                view.setTitle(view.getResources().getString(R.string.navigation_part_map_name));
                view.setCurrentItem(2);
                break;
        }
    }


    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
