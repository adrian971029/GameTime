package com.adrian971029.gametime.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.adrian971029.gametime.base.BaseActivity;
import com.adrian971029.gametime.R;
import com.adrian971029.gametime.database.DbConexoes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_toqueIniciar)
    TextView tvToqueIniciar;

    private Context context;
    private Resources resources;
    private MediaPlayer mediaPlayer;
    public static DbConexoes dbConexaoSqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        context = getApplicationContext();
        resources = context.getResources();

        mediaPlayer = MediaPlayer.create(context, R.raw.button_pressed_sound);

        animationToqueInicial(tvToqueIniciar);

    }

    @Override
    protected synchronized void onStart() {
        super.onStart();
        iniciar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbConexaoSqlite != null) {
            dbConexaoSqlite.closeDbConexoes();
        }
    }

    private synchronized void iniciar() {
        dbConexaoSqlite = DbConexoes.getInstance();
    }

    @OnClick(R.id.ly_telaInicial)
    void onActionTelaInicial() {
        mediaPlayer.start();
        Intent intent = new Intent(context, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        tvToqueIniciar.clearAnimation();
        createDialogSair(this, resources.getString(R.string.lbl_deseja_sair), tvToqueIniciar);
    }

}
