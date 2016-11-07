package xyz.lebot.tub.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.lebot.tub.R;
import xyz.lebot.tub.ui.adapter.MainActivityFragmentPagerAdapter;
import xyz.lebot.tub.ui.navigator.Navigator;
import xyz.lebot.tub.ui.navigator.NavigatorImpl;
import xyz.lebot.tub.ui.view.CustomViewPager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.activity_main_bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.activity_main_view_pager)
    CustomViewPager viewPager;

    @BindView(R.id.activity_main_app_bar_toolbar)
    Toolbar toolbar;

    private PagerAdapter mPagerAdapter;
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPagerAdapter = new MainActivityFragmentPagerAdapter(getSupportFragmentManager());


        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPagingEnabled(false);

        navigator = new NavigatorImpl(this, viewPager, (MainActivityFragmentPagerAdapter) mPagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.activity_main_bottom_navigation_line_action:
                        navigator.navigateToPartLine();
                        break;
                    case R.id.activity_main_bottom_navigation_stop_action:
                        navigator.navigateToPartStop();
                        break;
                    case R.id.activity_main_bottom_navigation_map_action:
                        navigator.navigateToPartMap();
                        break;
                }
                return true;
            }
        });
        navigator.navigateToPartLine();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
        toolbar.setTitle(title);
    }
}
