package fr.bourgmapper.tub.ui.composition;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;

/**
 * Created by axell on 24/11/2016.
 */

public class ConnectionDialog implements ConnectionModule {

    private MaterialDialog.Builder materialDialogBuilder;

    public ConnectionDialog(Context context) {
        boolean wrapInScrollView = true;
        this.materialDialogBuilder = new MaterialDialog.Builder(context)
                .title(R.string.dialog_connection_title)
                .customView(R.layout.dialog_connection,wrapInScrollView)
                .positiveText(R.string.dialog_connection_positive_text)
                .negativeText(R.string.dialog_connection_negative_text);
    }

    @Override
    public void display() {
        MaterialDialog dialog= materialDialogBuilder.show();

        ButterKnife.bind(dialog.getView());
    }
}
