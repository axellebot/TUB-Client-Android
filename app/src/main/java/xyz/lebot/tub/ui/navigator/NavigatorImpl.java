package xyz.lebot.tub.ui.navigator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.io.Serializable;

import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.activity.MainActivity;
import xyz.lebot.tub.ui.adapter.MainActivityFragmentPagerAdapter;
import xyz.lebot.tub.ui.fragment.LineDetailFragment;
import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.MapFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;

/**
 * Created by axellebot on 07/11/2016.
 */

public class NavigatorImpl implements Navigator, Serializable {
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private MainActivityFragmentPagerAdapter pagerAdapter;

    public NavigatorImpl(MainActivity mainActivity, Navigator navigator, ViewPager viewPager, MainActivityFragmentPagerAdapter pagerAdapter) {
        this.mainActivity = mainActivity;
        this.viewPager = viewPager;
        this.pagerAdapter = pagerAdapter;
        this.pagerAdapter.setNavigator(navigator);
    }

    @Override
    public void initLinePart() {
        Class<?> fragmentClass = LineFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToLinePart(fragmentClass,args);
    }

    @Override
    public void initStopPart() {
        Class<?> fragmentClass = StopFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToStopPart(fragmentClass,args);
    }

    @Override
    public void initMapPart() {
        Class<?> fragmentClass = MapFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToMapPart(fragmentClass,args);
    }

    @Override
    public void navigateToPartLine() {
        viewPager.setCurrentItem(0);
        mainActivity.setSelecteBottomNavigation(0);
        mainActivity.setTitle(mainActivity.getResources().getString(R.string.navigation_part_line_name));
    }

    @Override
    public void navigateToPartStop() {
        viewPager.setCurrentItem(1);
        mainActivity.setTitle(mainActivity.getResources().getString(R.string.navigation_part_stop_name));
    }

    @Override
    public void navigateToPartMap() {
        viewPager.setCurrentItem(2);
        mainActivity.setTitle(mainActivity.getResources().getString(R.string.navigation_part_map_name));
    }

    @Override
    public void navigateBack() {
        switch (mainActivity.getSelectedBottomNavigation()) {
            case 0:
                navigateBackLinePart();
                break;
            case 1:
                navigateBackStopPart();
                break;
            case 2:
                navigateBackMapPart();
                break;
        }
    }

    public void navigateToLineDetail(String lineId) {
        navigateToPartLine();

        Class<?> fragmentClass = LineDetailFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);
        args.putString("LINE_ID", lineId);

        addFragmentClassWithBundleToLinePart(fragmentClass,args);
    }

    private void addFragmentClassWithBundleToLinePart(Class<?> fragmentClass,Bundle args){
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 0);
    }

    private void addFragmentClassWithBundleToStopPart(Class<?> fragmentClass,Bundle args){
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 1);
    }

    private void addFragmentClassWithBundleToMapPart(Class<?> fragmentClass,Bundle args){
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 0);
    }

    private void navigateBackLinePart(){
        pagerAdapter.dequeueFragmentClassStackForPosition(0);
    }
    private void navigateBackStopPart(){
        pagerAdapter.dequeueFragmentClassStackForPosition(1);
    }
    private void navigateBackMapPart(){
        pagerAdapter.dequeueFragmentClassStackForPosition(2);
    }
}
