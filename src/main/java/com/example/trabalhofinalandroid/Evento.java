package com.example.trabalhofinalandroid;

import android.provider.ContactsContract;

import java.io.Serializable;

public class Evento implements Serializable {
    private Integer id;
    private String nome;
    private String descricao;
    private String data;
    private String valor;
    private String qtvagas;
    private String local;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQtvagas() {
        return qtvagas;
    }

    public void setQtvagas(String qtvagas) {
        this.qtvagas = qtvagas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString(){
        return "Evento: " + nome + " / " + "Data: "  + data  + " / " + "Valor: " + valor  + " / " + "Vagas: " + qtvagas +  " / " + "Local do Evento: " + local;
    }
}
