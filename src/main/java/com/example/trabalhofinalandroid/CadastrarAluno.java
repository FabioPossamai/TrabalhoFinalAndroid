package com.example.trabalhofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarAluno extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private AlunoDAO dao;
    private Aluno aluno = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);
        nome = findViewById(R.id.editText);
        email = findViewById(R.id.editText3);
        dao = new AlunoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            email.setText(aluno.getEmail());
        }
    }

    public void salvar_aluno(View view) {

        if (aluno == null) {
            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setEmail(email.getText().toString());
            long id = dao.inserir(aluno);
            Toast.makeText(this, "Aluno Cadastrado com id: " + id, Toast.LENGTH_SHORT).show();
        }
    }
}
