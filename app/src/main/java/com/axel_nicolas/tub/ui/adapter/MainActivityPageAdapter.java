package com.axel_nicolas.tub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.axel_nicolas.tub.ui.fragment.LineFragment;
import com.axel_nicolas.tub.ui.fragment.StopFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MainActivityPageAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;

    public MainActivityPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LineFragment();
                break;
            case 1:
                fragment = new StopFragment();
                break;
            case 2:
                fragment = new LineFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }
}
