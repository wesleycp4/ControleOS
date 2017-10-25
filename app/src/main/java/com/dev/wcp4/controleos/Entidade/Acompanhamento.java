package com.dev.wcp4.controleos.Entidade;

public class Acompanhamento {

    private int idAcompanhamento;
    private String descricaoAcompanhamento;
    private String dataAcompanhamento;
    private String nomeFunc;
    private int idFunc;
    private int idOs;

    public Acompanhamento(String descricaoAcompanhamento, String dataAcompanhamento, String nomeFunc) {
        this.descricaoAcompanhamento = descricaoAcompanhamento;
        this.dataAcompanhamento = dataAcompanhamento;
        this.nomeFunc = nomeFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }

    public int getIdAcompanhamento() {
        return idAcompanhamento;
    }

    public void setIdAcompanhamento(int idAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
    }

    public String getDescricaoAcompanhamento() {
        return descricaoAcompanhamento;
    }

    public void setDescricaoAcompanhamento(String descricaoAcompanhamento) {
        this.descricaoAcompanhamento = descricaoAcompanhamento;
    }

    public String getDataAcompanhamento() {
        return dataAcompanhamento;
    }

    public void setDataAcompanhamento(String dataAcompanhamento) {
        this.dataAcompanhamento = dataAcompanhamento;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getIdOs() {
        return idOs;
    }

    public void setIdOs(int idOs) {
        this.idOs = idOs;
    }
}
