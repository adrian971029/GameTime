package com.adrian971029.gametime.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.base.BaseActivity;
import com.adrian971029.gametime.database.dao.PessoaDao;
import com.adrian971029.gametime.database.dto.Pessoa;
import com.adrian971029.gametime.helper.Constainst;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edtUsuario)
    TextInputEditText edtUsuario;
    @BindView(R.id.edtSenha)
    TextInputEditText edtSenha;
    @BindView(R.id.bt_entrar)
    MaterialButton btnEntrar;

    private ArrayList<Pessoa> pessoaArrayList = new ArrayList<>();
    private Pessoa usuario;
    private PessoaDao pessoaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new LoginTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

    @OnClick(R.id.bt_entrar)
    void actionEntrar() {
        if (isCamposNaoVacios()) {
            if (isUsuarioExistente()) {
                if (isSenhaValida()) {
                    Intent intent = new Intent(this, SelectionActivity.class);
                    intent.putExtra(Constainst.PESSOA, usuario);
                    startActivity(intent);
                } else {
                    edtUsuario.setError("Senha Inválida");
                }
            } else {
                edtUsuario.setError("Usuario inexistente");
            }
        } else {
            Toast.makeText(this, "Precisa prencher todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.bt_criar_conta)
    void actionCriarConta() {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private boolean isCamposNaoVacios() {
        return (!Objects.requireNonNull(edtUsuario.getText()).toString().equals("")) &&
                (!Objects.requireNonNull(edtSenha.getText()).toString().equals(""));
    }

    private boolean isUsuarioExistente() {
        if (!pessoaArrayList.isEmpty()) {
            for (Pessoa pessoa : pessoaArrayList) {
                if (pessoa.getNickname().equals(Objects.requireNonNull(edtUsuario.getText()).toString())) {
                    usuario = pessoa;
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean isSenhaValida() {
        return usuario.getSenha().equals(Objects.requireNonNull(edtUsuario.getText()).toString());
    }

    class LoginTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnEntrar.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            PessoaDao pessoaDao = new PessoaDao();
            pessoaArrayList = pessoaDao.obterTodasPessoas();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            btnEntrar.setEnabled(true);
        }
    }


}
