package xyz.lebot.tub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;

/**
 * Created by axell on 05/11/2016.
 */

public class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGES_COUNT = 3;

    private List<LinkedList<Class<?>>> stackFragmentList;

    private long baseId = 0;

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

    private Fragment getFragmentForPosition(int index) {
        Class<?> fragmentClass = this.stackFragmentList.get(index).getLast();
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
        stackFragmentList.get(index).addLast(fragmentClass);
        notifyChangeInPosition(index);
    }

    public void dequeueFragmentClassStackForPosition(int index) {
        if(stackFragmentList.get(index).size()>1){
            stackFragmentList.get(index).removeLast();
            notifyChangeInPosition(index);
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
}
