package xyz.lebot.tub.ui.navigator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by axellebot on 07/11/2016.
 */

public interface Navigator {
    void displayFragmentOnPart(Fragment fragment, int part);
}
