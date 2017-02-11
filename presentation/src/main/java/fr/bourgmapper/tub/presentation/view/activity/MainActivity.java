package fr.bourgmapper.tub.presentation.view.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.bourgmapper.tub.R;
import fr.bourgmapper.tub.presentation.navigator.MainNavigator;
import fr.bourgmapper.tub.presentation.view.composition.ConnectionDialogModule;
import fr.bourgmapper.tub.presentation.view.composition.ConnectionDialogModuleImpl;
import fr.bourgmapper.tub.presentation.view.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, InfoFragment.MainNavigationListener {
    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.activity_main_navigation_drawer)
    NavigationView navigationView;

    //BottomSheet
    @BindView(R.id.bottom_sheet_main)
    View bottomSheet;

    @BindView(R.id.activity_main_fab_menu)
    FloatingActionButton menuFloatingActionButton;

    private MainNavigator navigator;
    private ActionBarDrawerToggle drawerToggle;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigator = new MainNavigator(this);

        initNavigationDrawer();
        initBottomSheet();

        navigationView.setNavigationItemSelectedListener(this);

        navigator.displayHomeMapFragment();
    }

    private void initNavigationDrawer() {
        this.menuFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawerLayout.addDrawerListener(drawerToggle);
        this.drawerToggle.syncState();
    }

    private void initBottomSheet() {
        this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                checkFABMenu();
                checkStatusBarDim();
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                checkFABMenu();
                checkStatusBarDim();
            }
        });
    }

    private void checkStatusBarDim() {
        int diff = getStatusBarHeight() - getBottomSheetPosition();

        if (diff >= 0) {
            setStatusBarDim(false);
        } else {
            setStatusBarDim(true);
        }
    }

    private void checkFABMenu() {
        int diff = (getFABMenuPosition() + this.menuFloatingActionButton.getHeight()) - getBottomSheetPosition();

        if (diff >= 0) {
            menuFloatingActionButton.hide();
        } else {
            menuFloatingActionButton.show();
        }
    }

    private int getStatusBarHeight() {
        Rect rect = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    private int getBottomSheetPosition() {
        int[] location = new int[2];
        this.bottomSheet.getLocationOnScreen(location);
        int bottomSheetPositionX = location[0];
        int bottomSheetPositionY = location[1];
        return bottomSheetPositionY;
    }

    private int getFABMenuPosition() {
        int[] location = new int[2];
        this.menuFloatingActionButton.getLocationOnScreen(location);
        int menuFloatingActionButtonPositionX = location[0];
        int menuFloatingActionButtonPositionY = location[1];
        return menuFloatingActionButtonPositionY;
    }

    private void setStatusBarDim(boolean dim) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(
                    dim ? Color.TRANSPARENT : ContextCompat.getColor(this, R.color.colorPrimary)
            );
        }
    }

    @OnClick(R.id.activity_main_fab_close)
    public void onCloseFABClicked(View view) {
        navigator.onBackPressed();
    }

    public BottomSheetBehavior getBottomSheetBehavior() {
        return bottomSheetBehavior;
    }


    @Override
    public void onBackPressed() {
        navigator.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_drawer_log_in:
                ConnectionDialogModule connectionDialogModule = new ConnectionDialogModuleImpl(this);
                connectionDialogModule.display();
                break;
        }
        return false;
    }


    @Override
    public void onStopsButtonSelected() {
        navigator.displayStopListFragmentOverview();
    }

    @Override
    public void onLinesButtonSelected() {
        navigator.displayLineListFragmentOverview();
    }


}
