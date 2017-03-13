package fr.bourgmapper.tub.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.view.activity.MainActivity;
import fr.bourgmapper.tub.presentation.view.fragment.InfoFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.view.fragment.StopListFragment;

public class MainNavigator {
    private static String TAG = "MainNavigator";

    private FRAGMENT currentFragmentOverview;

    private FragmentManager fragmentManager;
    private MainActivity activity;
    private Context context;

    private MapFragment mapFragment;
    private InfoFragment infoFragment;
    private LineListFragment lineListFragment;
    private StopListFragment stopListFragment;

    private Intent shareIntent;
    private Intent contactIntent;

    public MainNavigator(MainActivity activity) {
        this.activity = activity;
        this.context = activity;
        this.fragmentManager = activity.getSupportFragmentManager();

        this.setupContactIntent();
        this.setupShareIntent();
    }

    public void onBackPressed() {
        Log.i(TAG, "Back pressed");
        if (currentFragmentOverview == FRAGMENT.INFO && activity.getBottomSheetBehavior().getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            activity.finish();
        } else {
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
        }
    }

    public void displayMapFragment() {
        if (this.mapFragment == null) {
            this.mapFragment = MapFragment.newInstance();
        }
        fragmentTransactionReplace(mapFragment);
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

    public void navigateToLineList() {
        if (currentFragmentOverview != FRAGMENT.LINE_LIST) {
            if (lineListFragment == null) {
                lineListFragment = LineListFragment.newInstance();
            }
            fragmentTransactionReplaceOverview(lineListFragment);
            currentFragmentOverview = FRAGMENT.LINE_LIST;
        }
    }

    public void navigateToStopList() {
        if (currentFragmentOverview != FRAGMENT.STOP_LIST) {
            if (stopListFragment == null) {
                stopListFragment = StopListFragment.newInstance();
            }
            fragmentTransactionReplaceOverview(stopListFragment);
            currentFragmentOverview = FRAGMENT.STOP_LIST;
        }
    }

    public void navigateToShareIntent() {
        context.startActivity(this.shareIntent);
    }

    public void navigateToContactIntent() {
        context.startActivity(Intent.createChooser(this.contactIntent, "Send Email"));
    }

    private void setupContactIntent() {
        this.contactIntent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_EMAIL, this.context.getString(R.string.intent_contact_receiver))
                .putExtra(Intent.EXTRA_SUBJECT, this.context.getString(R.string.intent_contact_subject))
                .putExtra(Intent.EXTRA_TEXT, this.context.getString(R.string.intent_contact_text_content));
    }

    private void setupShareIntent() {
        this.shareIntent = new Intent()
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, this.context.getString(R.string.intent_share_text_content))
                .setType("text/plain");
    }

    private void fragmentTransactionReplace(Fragment fragment) {
        this.fragmentManager.beginTransaction()
                .replace(R.id.content_main_fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }

    private void fragmentTransactionReplaceOverview(Fragment fragment) {
        this.fragmentManager.beginTransaction()
                .replace(R.id.bottom_sheet_main_fragment_container, fragment, fragment.getClass().getName())
                .commit();
    }

    private enum FRAGMENT {
        INFO,
        LINE_LIST,
        STOP_LIST
    }
}
