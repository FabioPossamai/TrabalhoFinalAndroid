package com.example.trabalhofinalandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public EventoDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Evento evento){
        ContentValues values = new ContentValues();
        values.put("nome", evento.getNome());
        values.put("descricao", evento.getDescricao());
        values.put("data", evento.getData());
        values.put("valor", evento.getValor());
        values.put("qtvagas", evento.getQtvagas());
        values.put("local", evento.getLocal());

       return banco.insert("evento",null,values);
    }

    public List<Evento> obterTodos(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = banco.query("evento", new String[]{"id","nome","descricao","data","valor","qtvagas","local"},null,null,null,null,null);
        while (cursor.moveToNext()){
            Evento e = new Evento();
            e.setId(cursor.getInt(0));
            e.setNome(cursor.getString(1));
            e.setDescricao(cursor.getString(2));
            e.setData(cursor.getString(3));
            e.setValor(cursor.getString(4));
            e.setQtvagas(cursor.getString(5));
            e.setLocal(cursor.getString(6));
            eventos.add(e);
            }
        return eventos;
    }

    public void excluir(Evento e){
        banco.delete("evento", "id = ?", new String[]{e.getId().toString()});
    }

    public void atulizar(Evento evento){
        ContentValues values = new ContentValues();
        values.put("nome", evento.getNome());
        values.put("descricao", evento.getDescricao());
        values.put("data", evento.getData());
        values.put("valor", evento.getValor());
        values.put("qtvagas", evento.getQtvagas());
        values.put("local", evento.getLocal());
        banco.update("evento", values,"id = ?", new String[]{evento.getId().toString()});
    }
}
