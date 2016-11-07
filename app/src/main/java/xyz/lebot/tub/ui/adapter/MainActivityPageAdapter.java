package xyz.lebot.tub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xyz.lebot.tub.ui.activity.MainActivity;
import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MainActivityPageAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;


    private FragmentManager fragmentManager;
    private List<LinkedList<Fragment>> stackFragmentList;


    public MainActivityPageAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0 ; i<PAGES_COUNT;i++){
            stackFragmentList.add(new LinkedList<Fragment>());
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        fragment = getFragmentForPosition(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    private void addFragmentToStackForPosition(int index, Fragment fragment) {
        stackFragmentList.get(index).addFirst(fragment);
    }

    private Fragment getFragmentForPosition(int index) {
        return this.stackFragmentList.get(index).getFirst();
    }

    private Fragment popFragmentStackForPosition(int index){
        return this.stackFragmentList.get(index).pop();
    }
}
