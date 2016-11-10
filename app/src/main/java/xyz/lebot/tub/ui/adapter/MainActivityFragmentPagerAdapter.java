package xyz.lebot.tub.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;
import xyz.lebot.tub.ui.navigator.Navigator;

/**
 * Created by axell on 05/11/2016.
 */

public class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;

    private List<LinkedList<Class<?>>> stackFragmentList;
    private List<LinkedList<Bundle>> stackBundleList;
    private Navigator navigator;
    private Bundle bundle;

    private long baseId = 0;

    public MainActivityFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        stackFragmentList = new ArrayList<>();
        stackBundleList = new ArrayList<>();
        for (int i = 0; i < PAGES_COUNT; i++) {
            stackFragmentList.add(new LinkedList<Class<?>>());
            stackBundleList.add(new LinkedList<Bundle>());
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = getFragmentWithBundleForPosition(position);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return stackFragmentList.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }

    private Fragment getFragmentWithBundleForPosition(int position) {
        Class<?> fragmentClass = this.stackFragmentList.get(position).getLast();
        Bundle args = this.stackBundleList.get(position).getLast();

        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    public void addFragmentClassWithBundleToStackForPosition(Class<?> fragmentClass, Bundle args, int position) {
        addFragmentClassToStackForPosition(fragmentClass, position);
        addBundleToStackForPosition(args, position);
        notifyChangeInPosition(position);
    }

    public void addFragmentClassToStackForPosition(Class<?> fragmentClass, int position) {
        stackFragmentList.get(position).addLast(fragmentClass);
    }

    public void addBundleToStackForPosition(Bundle args, int position) {
        stackBundleList.get(position).addLast(args);
    }

    public void dequeueFragmentClassStackForPosition(int position) {
        if (stackFragmentList.get(position).size() > 1) {
            stackFragmentList.get(position).removeLast();
            stackBundleList.get(position).removeLast();
            notifyChangeInPosition(position);
        }
    }

    /**
     * Notify that the position of a fragment has been changed.
     * Create a new ID for each position to force recreation of the fragment
     *
     * @param n number of items which have been changed
     */
    public void notifyChangeInPosition(int n) {
        baseId += getCount() + n;
        notifyDataSetChanged();
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
