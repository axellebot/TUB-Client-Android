package xyz.lebot.tub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;


    private List<LinkedList<Class<?>>> stackFragmentList;


    public MainActivityFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        stackFragmentList = new ArrayList<>();
        for (int i = 0; i < PAGES_COUNT; i++) {
            stackFragmentList.add(new LinkedList<Class<?>>());
        }

        addFragmentClassToStackForPosition(LineFragment.class, 0);
        addFragmentClassToStackForPosition(StopFragment.class, 1);
        addFragmentClassToStackForPosition(MapFragment.class, 2);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragmentForPosition(position);
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }


    private Fragment getFragmentForPosition(int index) {

        Class<?> fragmentClass = this.stackFragmentList.get(index).getFirst();
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public void addFragmentClassToStackForPosition(Class<?> fragmentClass, int index) {
        stackFragmentList.get(index).addFirst(fragmentClass);
    }

    public Class<?> popFragmentClassStackForPosition(int index) {
        return this.stackFragmentList.get(index).pop();
    }


}
