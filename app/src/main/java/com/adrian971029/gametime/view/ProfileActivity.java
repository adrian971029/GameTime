package com.adrian971029.gametime.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.base.BaseActivity;
import com.adrian971029.gametime.database.dto.Pessoa;
import com.adrian971029.gametime.helper.Constainst;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @BindView(R.id.tv_infoNickname)
    TextView tvNickname;
    @BindView(R.id.tv_infoNome)
    TextView tvNome;
    @BindView(R.id.tv_infoEmail)
    TextView tvEmail;
    @BindView(R.id.tv_infoPontos)
    TextView tvPontos;
    @BindView(R.id.tv_infoLevelTilulo)
    TextView tvLevelTitulo;
    @BindView(R.id.rtb_level)
    RatingBar ratingBar;

    private Pessoa usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getParcelable(Constainst.PESSOA) != null) {
                usuario = bundle.getParcelable(Constainst.PESSOA);
                prencherInfo(usuario);
            }
        }

    }

    @OnClick(R.id.bt_senha)
    void onActionSenha() {

    }

    @OnClick(R.id.bt_voltar)
    void onActionVoltar() {
        finish();
    }

    private void prencherInfo(Pessoa pessoa) {
        tvNickname.setText(pessoa.getNickname());
        tvNome.setText(String.format("%s %s", pessoa.getNome(), pessoa.getSobrenome()));
        tvEmail.setText(pessoa.getEmail());
        tvPontos.setText("0 Pontos");
        ratingBar.setRating(1);
        tvLevelTitulo.setText("Iniciante");
    }

}
