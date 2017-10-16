package com.dev.wcp4.controleos.Entidades;

public class Acompanhamento {

    private int idAcompanhamento;
    private String descricaoAcompanhamento;
    private String dataAcompanhamento;
    private int idFunc;
    private int idOs;

    public Acompanhamento(int idAcompanhamento, String descricaoAcompanhamento, String dataAcompanhamento) {
        this.idAcompanhamento = idAcompanhamento;
        this.descricaoAcompanhamento = descricaoAcompanhamento;
        this.dataAcompanhamento = dataAcompanhamento;
    }

    public Acompanhamento(int idAcompanhamento, String descricaoAcompanhamento, String dataAcompanhamento, int idFunc, int idOs) {
        this.idAcompanhamento = idAcompanhamento;
        this.descricaoAcompanhamento = descricaoAcompanhamento;
        this.dataAcompanhamento = dataAcompanhamento;
        this.idFunc = idFunc;
        this.idOs = idOs;
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
