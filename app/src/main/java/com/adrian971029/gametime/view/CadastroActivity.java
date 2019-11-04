package com.adrian971029.gametime.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.base.BaseActivity;
import com.adrian971029.gametime.database.dao.PessoaDao;
import com.adrian971029.gametime.database.dto.Pessoa;
import com.adrian971029.gametime.helper.Constainst;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends BaseActivity {

    @BindView(R.id.edtNome)
    TextInputEditText edtNome;
    @BindView(R.id.edtSobrenome)
    TextInputEditText edtSobrenome;
    @BindView(R.id.edtNickname)
    TextInputEditText edtNickname;
    @BindView(R.id.edtEmail)
    TextInputEditText edtEmail;
    @BindView(R.id.edtSenha)
    TextInputEditText edtSenha;
    @BindView(R.id.edtConfirmarSenha)
    TextInputEditText edtConfirmarSenha;
    @BindView(R.id.bt_cadastrar)
    MaterialButton btnCadastrar;

    private String nome, sobrenomae, nickname, email, senha, confirmarSenha;
    private Pessoa pessoa;
    private PessoaDao pessoaDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_cadastrar)
    void actionBtnCadastrar() {
        getInputValues();
        if (isCamposNaoVacios()) {
            if (isSenhaEqualsConfirmarSenha()) {
                cadastrarUsuario();
            } else {
                edtConfirmarSenha.setError("Senhas não coincidem!");
            }
        } else {
            Toast.makeText(this, "Precisa prencher todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getInputValues() {
        nome = edtNome.getText().toString();
        sobrenomae = edtSobrenome.getText().toString();
        nickname = edtNickname.getText().toString();
        email = edtEmail.getText().toString();
        senha = edtSenha.getText().toString();
        confirmarSenha = edtConfirmarSenha.getText().toString();
    }

    private boolean isCamposNaoVacios() {
        return (!nome.equals("")) && (!sobrenomae.equals("")) && (!nickname.equals("")) &&
                (!email.equals("")) && (!senha.equals("")) && (!confirmarSenha.equals(""));
    }

    private boolean isSenhaEqualsConfirmarSenha() {
        return senha.equals(confirmarSenha);
    }

    private void cadastrarUsuario() {
        pessoa = new Pessoa(nickname, nome, sobrenomae, email, senha);
        pessoaDao = new PessoaDao();
        pessoaDao.inserirPessoa(pessoa);
        Intent intent = new Intent(CadastroActivity.this, SelectionActivity.class);
        intent.putExtra(Constainst.PESSOA, pessoa);
        startActivity(intent);
    }

}
