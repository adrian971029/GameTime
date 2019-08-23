package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.adrian971029.gametime.base.BaseFragment;
import com.adrian971029.gametime.R;
import com.adrian971029.gametime.adapter.ScreenSlidePagerAdapter;
import com.adrian971029.gametime.helper.ZoomOutPageTransformer;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener{

    public static final int NUM_PAGES = 3;

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;

    private Context context;
    private Resources resources;
    private PagerAdapter pagerAdapter;
    private MediaPlayer mediaPlayerChangePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(SelectionActivity.this);

        mediaPlayerChangePage = MediaPlayer.create(getApplicationContext(), R.raw.swipe_pager_sound);
        setupPager();

    }

    private void setupPager() {
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), context);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mediaPlayerChangePage.start();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            createDialogSair(this, resources.getString(R.string.lbl_deseja_sair));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
