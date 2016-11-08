package xyz.lebot.tub.ui.navigator;

import android.support.v4.view.ViewPager;

import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.activity.MainActivity;
import xyz.lebot.tub.ui.adapter.MainActivityFragmentPagerAdapter;
import xyz.lebot.tub.ui.fragment.LineDetailFragment;

/**
 * Created by axellebot on 07/11/2016.
 */

public class NavigatorImpl implements Navigator {
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private MainActivityFragmentPagerAdapter pagerAdapter;

    public NavigatorImpl(MainActivity mainActivity, ViewPager viewPager, MainActivityFragmentPagerAdapter pagerAdapter) {
        this.mainActivity = mainActivity;
        this.viewPager = viewPager;
        this.pagerAdapter = pagerAdapter;
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
        switch (mainActivity.getSelectedBottomNavigation()){
            case 0:
                pagerAdapter.dequeueFragmentClassStackForPosition(0);
                break;
            case 1:
                pagerAdapter.dequeueFragmentClassStackForPosition(1);
                break;
            case 2:
                pagerAdapter.dequeueFragmentClassStackForPosition(2);
                break;
        }
    }

    public void navigateToLineDetail(){
        navigateToPartLine();
        pagerAdapter.addFragmentClassToStackForPosition(LineDetailFragment.class,0);
    }
}
