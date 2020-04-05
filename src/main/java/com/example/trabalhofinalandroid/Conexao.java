package com.example.trabalhofinalandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table evento(id integer primary key autoincrement," +
            "nome varchar(50), " +
            "descricao varchar(50), " +
            "data varchar(9)," +
            "valor varchar(10), " +
            "qtvagas varchar(50), " +
            "local varchar (50) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
