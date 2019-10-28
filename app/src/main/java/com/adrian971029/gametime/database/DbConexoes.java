package com.adrian971029.gametime.database;

import android.database.sqlite.SQLiteDatabase;

import com.adrian971029.gametime.App;

public class DbConexoes {

    private static DbConexoes dbConexoes;
    private SQLiteDatabase db;

    private DbConexoes() {
        GameTimeContract helper = GameTimeContract.getInstance(App.getInstance().getApplicationContext());
        db = helper.getWritableDatabase();
    }

    public static DbConexoes getInstance() {
        if(dbConexoes == null) {
            dbConexoes = new DbConexoes();
        }
        return dbConexoes;
    }

    public SQLiteDatabase getDatabase(){
        if (db != null && db.isOpen()) {
            return this.db;
        } else {
            return GameTimeContract.getInstance(App.getInstance().getApplicationContext()).getWritableDatabase();
        }

    }

    public void closeDbConexoes() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        dbConexoes = null;
    }

}
