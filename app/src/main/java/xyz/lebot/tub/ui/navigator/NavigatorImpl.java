package xyz.lebot.tub.ui.navigator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.io.Serializable;

import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.activity.MainActivity;
import xyz.lebot.tub.ui.adapter.MainActivityFragmentPagerAdapter;
import xyz.lebot.tub.ui.fragment.HomeFragment;
import xyz.lebot.tub.ui.fragment.LineDetailFragment;
import xyz.lebot.tub.ui.fragment.LineFragment;
import xyz.lebot.tub.ui.fragment.StopFragment;

/**
 * Created by axellebot on 07/11/2016.
 */

public class NavigatorImpl implements Navigator, Serializable {
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private MainActivityFragmentPagerAdapter pagerAdapter;
    private int SELECTED_PART;
    public NavigatorImpl(MainActivity mainActivity, Navigator navigator, ViewPager viewPager, MainActivityFragmentPagerAdapter pagerAdapter) {
        this.mainActivity = mainActivity;
        this.viewPager = viewPager;
        this.pagerAdapter = pagerAdapter;
        this.pagerAdapter.setNavigator(navigator);
    }

    @Override
    public void initHomePart() {
        Class<?> fragmentClass = HomeFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToMapPart(fragmentClass, args);
    }

    @Override
    public void initLinePart() {
        Class<?> fragmentClass = LineFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToLinePart(fragmentClass, args);
    }

    @Override
    public void initStopPart() {
        Class<?> fragmentClass = StopFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);

        addFragmentClassWithBundleToStopPart(fragmentClass, args);
    }

    @Override
    public void navigateToPartHome() {
        SELECTED_PART = 0;
        viewPager.setCurrentItem(0);
        mainActivity.setSelecteBottomNavigation(0);
        mainActivity.setToolbarTitle(mainActivity.getResources().getString(R.string.navigation_part_map_name));
        int res = R.color.colorPrimaryBlue;
        changeColor(res);
    }

    @Override
    public void navigateToPartLine() {
        SELECTED_PART = 1;
        viewPager.setCurrentItem(1);
        mainActivity.setSelecteBottomNavigation(1);
        mainActivity.setToolbarTitle(mainActivity.getResources().getString(R.string.navigation_part_line_name));
        int res = R.color.colorPrimaryGreen;
        changeColor(res);
    }

    @Override
    public void navigateToPartStop() {
        SELECTED_PART = 2;
        viewPager.setCurrentItem(2);
        mainActivity.setSelecteBottomNavigation(2);
        mainActivity.setToolbarTitle(mainActivity.getResources().getString(R.string.navigation_part_stop_name));
        int res = R.color.colorPrimaryRed;
        changeColor(res);
    }

    @Override
    public void navigateBack() {
        switch (SELECTED_PART) {
            case 0:
                navigateBackHomePart();
                break;
            case 1:
                navigateBackLinePart();
                break;
            case 2:
                navigateBackStopPart();
                break;
        }
    }

    public void navigateToLineDetail(String lineId) {
        navigateToPartLine();

        Class<?> fragmentClass = LineDetailFragment.class;

        Bundle args = new Bundle();
        args.putSerializable("NAVIGATOR", this);
        args.putString("LINE_ID", lineId);

        addFragmentClassWithBundleToLinePart(fragmentClass, args);
    }

    private void addFragmentClassWithBundleToMapPart(Class<?> fragmentClass, Bundle args) {
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 0);
    }

    private void addFragmentClassWithBundleToLinePart(Class<?> fragmentClass, Bundle args) {
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 1);
    }

    private void addFragmentClassWithBundleToStopPart(Class<?> fragmentClass, Bundle args) {
        pagerAdapter.addFragmentClassWithBundleToStackForPosition(fragmentClass, args, 2);
    }


    private void navigateBackHomePart() {
        pagerAdapter.dequeueFragmentClassStackForPosition(0);
    }

    private void navigateBackLinePart() {
        pagerAdapter.dequeueFragmentClassStackForPosition(1);
    }

    private void navigateBackStopPart() {
        pagerAdapter.dequeueFragmentClassStackForPosition(2);
    }


    private void changeColor(int res) {
        int color = mainActivity.getResources().getColor(res);
        mainActivity.setToolbarColor(color);
        mainActivity.setBottomNavigationColor(res);
    }
}
