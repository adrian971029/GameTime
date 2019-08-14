package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.adrian971029.gametime.base.BaseFragment;
import com.adrian971029.gametime.R;
import com.adrian971029.gametime.adapter.ScreenSlidePagerAdapter;
import com.adrian971029.gametime.helper.ZoomOutPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends BaseFragment {

    public static final int NUM_PAGES = 3;

    @BindView(R.id.pager)
    ViewPager mPager;

    private Context context;
    private Resources resources;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();

        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), context);
        mPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onBackPressed() {
        createDialogSair(this, resources.getString(R.string.lbl_deseja_sair));
    }

}
