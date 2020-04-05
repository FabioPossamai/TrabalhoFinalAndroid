package com.example.trabalhofinalandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private ConexaoAluno conexaoAluno;
    private SQLiteDatabase banco;

    public AlunoDAO(Context context){
        conexaoAluno = new ConexaoAluno(context);
        banco = conexaoAluno.getWritableDatabase();
    }

    public long inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("email", aluno.getEmail());

        return banco.insert("aluno",null,values);
    }

    public List<Aluno> obterTodos(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno", new String[]{"id","nome","email"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Aluno e = new Aluno();
            e.setId(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            e.setEmail(cursor.getString(2));
            alunos.add(e);
        }
        return alunos;
    }
}
