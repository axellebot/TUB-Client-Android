package fr.bourgmapper.tub.presentation.navigator;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.ui.activity.BaseLifeCycle;
import fr.bourgmapper.tub.presentation.ui.activity.MainActivity;
import fr.bourgmapper.tub.presentation.ui.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.InfoFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.ui.fragment.StopListFragment;

/**
 * Created by axell on 01/02/2017.
 */

public class MainNavigator implements BaseLifeCycle {
    private static String TAG = "MainNavigator";

    private FRAGMENT currentFragment;
    private FRAGMENT currentFragmentOverview;
    private FragmentManager fragmentManager;
    private MainActivity activity;

    private MapFragment mapFragment;
    private InfoFragment infoFragment;
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
        Log.i(TAG, "Back pressed");
        switch (currentFragment) {
            case HOME_MAP:
                switch (currentFragmentOverview) {
                    case INFO:
                        activity.getBottomSheetBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case LINE_LIST:
                        displayInfoFragmentOverview();
                        break;
                    case STOP_LIST:
                        displayInfoFragmentOverview();
                        break;
                }
                break;
        }
    }


    public void displayHomeMapFragment() {
        if (currentFragment != FRAGMENT.HOME_MAP) {
            if (this.mapFragment == null) {
                this.mapFragment = MapFragment.newInstance();
            }
            MapFragment mapFragment = new MapFragment();
            fragmentTransactionReplace(mapFragment);
            currentFragment = FRAGMENT.HOME_MAP;
        }

        displayInfoFragmentOverview();
    }

    public void displayInfoFragmentOverview() {
        if (currentFragmentOverview != FRAGMENT.INFO) {
            if (infoFragment == null) {
                infoFragment = InfoFragment.newInstance();
            }
            fragmentTransactionReplaceOverview(infoFragment);
            currentFragmentOverview = FRAGMENT.INFO;
        }
        activity.getBottomSheetBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void displayLineListFragmentOverview() {
        if (currentFragmentOverview != FRAGMENT.LINE_LIST) {
            if (lineListFragment == null) {
                lineListFragment = lineListFragment.newInstance();
            }
            fragmentTransactionReplaceOverview(lineListFragment);
            currentFragmentOverview = FRAGMENT.LINE_LIST;
        }
    }

    public void displayStopListFragmentOverview() {
        if (currentFragmentOverview != FRAGMENT.STOP_LIST) {
            if (stopListFragment == null) {
                stopListFragment = StopListFragment.newInstance();
            }
            StopListFragment stopListFragment = new StopListFragment();
            fragmentTransactionReplaceOverview(stopListFragment);
            currentFragmentOverview = FRAGMENT.STOP_LIST;
        }
    }

    private Fragment getCurrentFragment() {
        return fragmentManager.findFragmentById(R.id.content_main_fragment_container);
    }

    private void fragmentTransactionReplace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void fragmentTransactionAdd(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_main_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.commit();
    }

    private void fragmentTransactionPop() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_out, android.R.anim.fade_in);
        fragmentTransaction.commit();
    }

    private void fragmentTransactionReplaceOverview(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottom_sheet_main_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void fragmentTransactionAddOverview(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.bottom_sheet_main_fragment_container, fragment, fragment.getClass().getName());
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.commit();
    }

    private void fragmentTransactionPopOverview() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_out, android.R.anim.fade_in);
        fragmentTransaction.commit();
    }

    public static enum FRAGMENT {
        HOME_MAP,
        INFO,
        LINE_LIST,
        STOP_LIST
    }
}
