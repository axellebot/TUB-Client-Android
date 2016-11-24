package xyz.lebot.tub.ui.composition;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;

/**
 * Created by axell on 24/11/2016.
 */

public class ConnectionDialog implements ConnectionModule {

    @BindView(R.id.dialog_connection_facebook_login_button)
    LoginButton facebookLoginAction;
    private MaterialDialog.Builder materialDialogBuilder;

    public ConnectionDialog(Context context) {
        boolean wrapInScrollView = true;
        this.materialDialogBuilder = new MaterialDialog.Builder(context)
                .title(R.string.dialog_connection_title)
                .customView(R.layout.dialog_connection,wrapInScrollView)
                .negativeText(R.string.dialog_connection_negative_text);
    }


    @Override
    public void display() {
        MaterialDialog dialog= materialDialogBuilder.show();

        ButterKnife.bind(dialog.getView());
    }
}
