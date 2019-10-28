package com.adrian971029.gametime.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameTimeContract extends SQLiteOpenHelper {

    private static GameTimeContract helper;

    private static final String DATABASE_NAME    = "gametime.db";
    private static final int DATABASE_VERSION = 1;

    public static synchronized GameTimeContract getInstance(Context context)
    {
        if(helper == null)
        {
            helper = new GameTimeContract(context);
        }

        return helper;
    }

    private GameTimeContract(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABELA_PESSOA  			        = "Pessoa";
    public static final String COLUNA_ID_PESSOA    			    = "idPessoa";
    public static final String COLUNA_NICKNAME_PESSOA  			= "nicknamePessoa";
    public static final String COLUNA_NOME_PESSOA  			    = "nomePessoa";
    public static final String COLUNA_SOBRENOME_PESSOA  	    = "sobrenomePessoa";
    public static final String COLUNA_EMAIL_PESSOA  			= "emailPessoa";
    public static final String COLUNA_SENHA_PESSOA  			= "senhaPessoa";

    private static final String DB_CREATE_TABELA_PESSOA = "create table "
            + TABELA_PESSOA + "(" + COLUNA_ID_PESSOA + " integer primary key autoincrement, "
            + COLUNA_NICKNAME_PESSOA + " text, "
            + COLUNA_NOME_PESSOA + " text, "
            + COLUNA_SOBRENOME_PESSOA + " text, "
            + COLUNA_EMAIL_PESSOA + " text, "
            + COLUNA_SENHA_PESSOA + " text);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_TABELA_PESSOA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PESSOA);
        onCreate(db);
    }

}
