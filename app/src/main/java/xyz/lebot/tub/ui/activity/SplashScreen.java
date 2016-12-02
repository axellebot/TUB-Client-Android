package xyz.lebot.tub.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import xyz.lebot.tub.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.graphics.PixelFormat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class SplashScreen extends AppCompatActivity {
    static int SPLASH_TIME_OUT = 3000;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    /**
     * Called when the activity is first created.
     */
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StartAnimations();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.left_splash_layout);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.expand);
                ImageView shadow = (ImageView) findViewById(R.id.splachScreen_shadow);
                shadow.setVisibility(View.VISIBLE);
                anim.reset();
                shadow.clearAnimation();
                shadow.startAnimation(anim);

            }
        }, 2000);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();


                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
}

