package com.example.trabalhofinalandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoAluno extends SQLiteOpenHelper {
    private static final String name = "aluno.db";
    private static final int version = 1;

    public ConexaoAluno(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aluno(id integer primary key autoincrement," +
                "nome varchar(50), " +
                "email varchar (50) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
