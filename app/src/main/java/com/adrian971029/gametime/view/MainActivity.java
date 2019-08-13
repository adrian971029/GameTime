package com.adrian971029.gametime.view;

import android.os.Bundle;

import com.adrian971029.gametime.BaseActivity;
import com.adrian971029.gametime.R;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

}
