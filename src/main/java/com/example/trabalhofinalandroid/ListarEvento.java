package com.example.trabalhofinalandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListarEvento extends AppCompatActivity {


    private ListView listview;
    private EventoDAO dao;
    private List<Evento> eventos;
    private List<Evento> eventosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_evento);

        listview = findViewById(R.id.lista_evento);
        dao = new EventoDAO(this);
        eventos = dao.obterTodos();
        eventosFiltrados.addAll(eventos);
        //adaptador
        ArrayAdapter<Evento> adaptador = new ArrayAdapter<Evento>(this, android.R.layout.simple_list_item_1, eventosFiltrados);
        listview.setAdapter(adaptador);
        registerForContextMenu(listview);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal,menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarEvento(s);
                return false;
            }
        });
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procurarEvento(String nome){
        eventosFiltrados.clear();
        for (Evento e : eventos){
            if(e.getNome().toLowerCase().contains(nome.toLowerCase())){
                eventosFiltrados.add(e);
            }
        }
        listview.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //pegando a posição da lista
        final Evento eventoExcluir = eventosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção")
                .setMessage("Realmente deseja excluir esse evento?")
                .setNegativeButton("NÂO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventosFiltrados.remove(eventoExcluir);
                        eventos.remove(eventoExcluir);
                        dao.excluir(eventoExcluir);
                        listview.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroEvento.class);
    startActivity(it);
    }
    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //pegando a posição da lista
        final Evento eventoAtualizar = eventosFiltrados.get(menuInfo.position);
        Intent it  = new Intent(this,CadastroEvento.class);
        it.putExtra("evento",eventoAtualizar);
        startActivity(it);
    }

    public void visualizar(MenuItem item){
        Intent it = new Intent(this, Listar_Aluno.class);
        startActivity(it);
    }
    @Override
    public void onResume(){
        super.onResume();
        eventos = dao.obterTodos();
        eventosFiltrados.clear();
        eventosFiltrados.addAll(eventos);
        listview.invalidateViews();
    }
}
