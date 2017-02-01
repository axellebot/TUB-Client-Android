package fr.bourgmapper.tub.presentation.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.ui.activity.BaseActivityLifeCycle;
import fr.bourgmapper.tub.presentation.ui.activity.MainActivity;
import fr.bourgmapper.tub.presentation.ui.fragment.HomeMapFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.StopListFragment;

/**
 * Created by axell on 01/02/2017.
 */

public class MainNavigator implements BaseActivityLifeCycle {

    private FRAGMENT currentFragment;
    private FragmentManager fragmentManager;
    private MainActivity activity;
    private HomeMapFragment homeMapFragment;
    private LineListFragment lineListFragment;
    private StopListFragment stopListFragment;
    public MainNavigator(MainActivity activity) {
        this.activity = activity;
        this.fragmentManager = ((MainActivity) activity).getSupportFragmentManager();
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    public void onBackPressed() {
        switch (currentFragment) {
            case HOME_MAP:

                break;
            case LINE_LIST:
                break;
            case STOP_LIST:
                break;
        }
    }

    public void displayHomeMapFragment() {
        if (currentFragment != FRAGMENT.HOME_MAP) {
            if (homeMapFragment == null) {
                homeMapFragment = HomeMapFragment.newInstance();
            }
            HomeMapFragment homeMapFragment = new HomeMapFragment();
            fragmentTransactionReplace(homeMapFragment);
            currentFragment = FRAGMENT.HOME_MAP;

            activity.setToolbarTitle(activity.getResources().getString(R.string.navigation_part_home_name));
            int res = R.color.colorPartHome;
            activity.setContextColor(res);
        }
    }

    public void displayLineListFragment() {
        if (currentFragment != FRAGMENT.LINE_LIST) {
            if (lineListFragment == null) {
                lineListFragment = lineListFragment.newInstance();
            }
            fragmentTransactionReplace(lineListFragment);
            currentFragment = FRAGMENT.LINE_LIST;

            activity.setToolbarTitle(activity.getResources().getString(R.string.navigation_part_line_name));
            int res = R.color.colorPartLine;
            activity.setContextColor(res);
        }
    }

    public void displayStopListFragment() {
        if (currentFragment != FRAGMENT.STOP_LIST) {
            if (stopListFragment == null) {
                stopListFragment = StopListFragment.newInstance();
            }
            StopListFragment stopListFragment = new StopListFragment();
            fragmentTransactionReplace(stopListFragment);
            currentFragment = FRAGMENT.STOP_LIST;

            activity.setToolbarTitle(activity.getResources().getString(R.string.navigation_part_stop_name));
            int res = R.color.colorPartStop;
            activity.setContextColor(res);
        }
    }

    private Fragment getCurrentFragment() {
        return fragmentManager.findFragmentById(R.id.main_activity_fragment_container);
    }

    private void fragmentTransactionReplace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void fragmentTransactionAdd(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.commit();
    }

    public static enum FRAGMENT {
        HOME_MAP,
        LINE_LIST,
        STOP_LIST
    }
}
