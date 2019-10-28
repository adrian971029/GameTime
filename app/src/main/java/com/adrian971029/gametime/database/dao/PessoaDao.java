package com.adrian971029.gametime.database.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.adrian971029.gametime.database.GameTimeContract;
import com.adrian971029.gametime.database.dto.Pessoa;
import com.adrian971029.gametime.view.MainActivity;

import java.util.ArrayList;

public class PessoaDao {

    public void inserirPessoa(Pessoa pessoa) {
        ContentValues values = new ContentValues();
        values.put(GameTimeContract.COLUNA_ID_PESSOA, pessoa.getId());
        values.put(GameTimeContract.COLUNA_NICKNAME_PESSOA, pessoa.getNickname());
        values.put(GameTimeContract.COLUNA_NOME_PESSOA, pessoa.getNome());
        values.put(GameTimeContract.COLUNA_SOBRENOME_PESSOA, pessoa.getSobrenome());
        values.put(GameTimeContract.COLUNA_EMAIL_PESSOA, pessoa.getEmail());
        values.put(GameTimeContract.COLUNA_SENHA_PESSOA, pessoa.getSenha());

        MainActivity.dbConexaoSqlite.getDatabase().insert(GameTimeContract.TABELA_PESSOA, null, values);
    }

    public ArrayList<Pessoa> obterTodasPessoas() {
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();
        Cursor mCursor = MainActivity.dbConexaoSqlite.getDatabase().query(
                true,
                GameTimeContract.TABELA_PESSOA,
                new String[]{GameTimeContract.COLUNA_ID_PESSOA, GameTimeContract.COLUNA_NICKNAME_PESSOA, GameTimeContract.COLUNA_NOME_PESSOA,
                        GameTimeContract.COLUNA_SOBRENOME_PESSOA, GameTimeContract.COLUNA_EMAIL_PESSOA, GameTimeContract.COLUNA_SENHA_PESSOA},
                null, null, null, null, GameTimeContract.COLUNA_ID_PESSOA, null);

        if(mCursor == null || mCursor.getCount() < 1) {
            if(mCursor != null) mCursor.close();
            return null;
        }
        else {
            mCursor.moveToFirst();
            while(!mCursor.isAfterLast()) {
                Pessoa pessoa = cursorToRegistro(mCursor);
                listaPessoa.add(pessoa);
                mCursor.moveToNext();
            }
            mCursor.close();
            return listaPessoa;
        }
    }

    private Pessoa cursorToRegistro(Cursor cursor) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(cursor.getInt(0));
        pessoa.setNickname(cursor.getString(1));
        pessoa.setNome(cursor.getString(2));
        pessoa.setSobrenome(cursor.getString(3));
        pessoa.setEmail(cursor.getString(4));
        pessoa.setSenha(cursor.getString(5));
        return pessoa;
    }

}
