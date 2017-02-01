package fr.bourgmapper.tub.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.navigator.MainNavigator;
import fr.bourgmapper.tub.presentation.ui.composition.ConnectionDialogModule;
import fr.bourgmapper.tub.presentation.ui.composition.ConnectionDialogModuleImpl;
import fr.bourgmapper.tub.presentation.ui.view.CustomBottomNavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.activity_main_bottom_navigation)
    CustomBottomNavigationView bottomNavigationView;


    @BindView(R.id.activity_main_app_bar_toolbar)
    Toolbar toolbar;

    private MainNavigator navigator;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigator = new MainNavigator(this);

        initStatusBar();


        //Navigation Drawer
        this.mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);

        navigator.displayHomeMapFragment();
    }

    private void initStatusBar() {
        //get statusBar height
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.height = params.height + statusBarHeight;
        toolbar.setLayoutParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (this.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.activity_main_manu_action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        navigator.onBackPressed();
    }

    public void setContextColor(int res) {
        int color = ContextCompat.getColor(this, res);
        setToolbarColor(color);
        setBottomNavigationColor(res);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    public void setBottomNavigationColor(int res) {
        bottomNavigationView.setItemBackgroundResource(res);
    }

    public void setSelecteBottomNavigation(int i) {
        bottomNavigationView.setSelected(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_bottom_navigation_home_map_action:
                navigator.displayHomeMapFragment();
                break;
            case R.id.activity_main_bottom_navigation_line_list_action:
                navigator.displayLineListFragment();
                break;
            case R.id.activity_main_bottom_navigation_stop_list_action:
                navigator.displayStopListFragment();
                break;
            case R.id.navigation_drawer_log_in:
                ConnectionDialogModule connectionDialogModule = new ConnectionDialogModuleImpl(this);
                connectionDialogModule.display();
                break;
        }
        return false;
    }
}
