package fr.bourgmapper.tub.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import javax.inject.Inject;

import fr.bourgmapper.tub.presentation.R;
import fr.bourgmapper.tub.presentation.view.activity.MainActivity;
import fr.bourgmapper.tub.presentation.view.composition.ConnectionDialogModule;
import fr.bourgmapper.tub.presentation.view.composition.ConnectionDialogModuleImpl;
import fr.bourgmapper.tub.presentation.view.fragment.InfoFragment;
import fr.bourgmapper.tub.presentation.view.fragment.LineListFragment;
import fr.bourgmapper.tub.presentation.view.fragment.MapFragment;
import fr.bourgmapper.tub.presentation.view.fragment.StopListFragment;

public class Navigator {
    private static String TAG = "Navigator";

    private ConnectionDialogModule connectionDialogModule;
    private Intent shareIntent;
    private Intent contactIntent;

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToInfo(Context context) {
    }

    public void navigateToLineList(Context context) {

    }

    public void navigateToStopList(Context context) {

    }

    public void navigateToConnectionDialog(Context context) {
        this.setupConnectionDialog(context);
        this.connectionDialogModule.display();
    }

    public void navigateToShareIntent(Context context) {
        this.setupShareIntent(context);
        context.startActivity(this.shareIntent);
    }

    public void navigateToContactIntent(Context context) {
        this.setupContactIntent(context);
        context.startActivity(Intent.createChooser(this.contactIntent, "Send Email"));
    }

    private void setupConnectionDialog(Context context) {
        this.connectionDialogModule = new ConnectionDialogModuleImpl(context);
    }

    private void setupContactIntent(Context context) {
        this.contactIntent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_EMAIL, context.getString(R.string.intent_contact_receiver))
                .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.intent_contact_subject))
                .putExtra(Intent.EXTRA_TEXT, context.getString(R.string.intent_contact_text_content));
    }

    private void setupShareIntent(Context context) {
        this.shareIntent = new Intent()
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, context.getString(R.string.intent_share_text_content))
                .setType("text/plain");
    }
}
