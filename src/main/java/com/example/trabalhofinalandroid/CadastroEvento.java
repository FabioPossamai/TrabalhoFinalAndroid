package com.example.trabalhofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroEvento extends AppCompatActivity {

    private EditText nome;
    private EditText descricao;
    private EditText data;
    private EditText valor;
    private EditText qtvagas;
    private EditText local;
    private EventoDAO dao;
    private Evento evento = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        //vincular o xml para receber os valores
        nome = findViewById(R.id.editText8);
        descricao = findViewById(R.id.editText9);
        data = findViewById(R.id.editText10);
        valor = findViewById(R.id.editText11);
        qtvagas = findViewById(R.id.editText12);
        local = findViewById(R.id.editText13);
        dao = new EventoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("evento")){
            evento = (Evento) it.getSerializableExtra("evento");
            nome.setText(evento.getNome());
            descricao.setText(evento.getDescricao());
            data.setText(evento.getData());
            valor.setText(evento.getValor());
            qtvagas.setText(evento.getQtvagas());
            local.setText(evento.getLocal());
        }
    }

    public void salvar(View view) {

        if (evento == null) {
            evento = new Evento();
            evento.setNome(nome.getText().toString());
            evento.setDescricao(descricao.getText().toString());
            evento.setData(data.getText().toString());
            evento.setValor(valor.getText().toString());
            evento.setQtvagas(qtvagas.getText().toString());
            evento.setLocal(local.getText().toString());
            long id = dao.inserir(evento);
            Toast.makeText(this, "Evento Cadastrado com id: " + id, Toast.LENGTH_SHORT).show();
        }else{
            evento.setNome(nome.getText().toString());
            evento.setDescricao(descricao.getText().toString());
            evento.setData(data.getText().toString());
            evento.setValor(valor.getText().toString());
            evento.setQtvagas(qtvagas.getText().toString());
            evento.setLocal(local.getText().toString());
            dao.atulizar(evento);
            Toast.makeText(this, "Evento foi atualizado com sucesso ", Toast.LENGTH_SHORT).show();
        }
    }
}
