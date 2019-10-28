package com.adrian971029.gametime.view;

import android.content.Intent;
import android.os.Bundle;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_criar_conta)
    void actionCriarConta() {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

}
