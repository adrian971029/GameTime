package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.adrian971029.gametime.BaseActivity;
import com.adrian971029.gametime.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_toqueIniciar)
    TextView tvToqueIniciar;

    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();

        animationToqueInicial(tvToqueIniciar);

    }

    @OnClick(R.id.ly_telaInicial)
    void onActionTelaInicial() {
        Intent intent = new Intent(context, SelectionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        tvToqueIniciar.clearAnimation();
        createDialogSair(this, resources.getString(R.string.lbl_deseja_sair), tvToqueIniciar);
    }
}
