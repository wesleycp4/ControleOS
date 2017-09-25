package com.dev.wcp4.controleos.Entidades;

import java.util.Date;

public class OrdemServico {

    private int idOS;
    private String descricaoOS;
    private String equipamentosOS;
    private Date dataAberturaOS;
    private Date dataFechamentoOS;
    private float valorOrcamentoOS;
    private float valorFinalOS;
    private int statusOS;

    public OrdemServico() {
    }

    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS, Date dataAberturaOS, Date dataFechamentoOS, float valorOrcamentoOS, float valorFinalOS, int statusOS) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.dataAberturaOS = dataAberturaOS;
        this.dataFechamentoOS = dataFechamentoOS;
        this.valorOrcamentoOS = valorOrcamentoOS;
        this.valorFinalOS = valorFinalOS;
        this.statusOS = statusOS;
    }

    public int getIdOS() {
        return idOS;
    }

    public void setIdOS(int idOS) {
        this.idOS = idOS;
    }

    public String getDescricaoOS() {
        return descricaoOS;
    }

    public void setDescricaoOS(String descricaoOS) {
        this.descricaoOS = descricaoOS;
    }

    public String getEquipamentosOS() {
        return equipamentosOS;
    }

    public void setEquipamentosOS(String equipamentosOS) {
        this.equipamentosOS = equipamentosOS;
    }

    public Date getDataAberturaOS() {
        return dataAberturaOS;
    }

    public void setDataAberturaOS(Date dataAberturaOS) {
        this.dataAberturaOS = dataAberturaOS;
    }

    public Date getDataFechamentoOS() {
        return dataFechamentoOS;
    }

    public void setDataFechamentoOS(Date dataFechamentoOS) {
        this.dataFechamentoOS = dataFechamentoOS;
    }

    public float getValorOrcamentoOS() {
        return valorOrcamentoOS;
    }

    public void setValorOrcamentoOS(float valorOrcamentoOS) {
        this.valorOrcamentoOS = valorOrcamentoOS;
    }

    public float getValorFinalOS() {
        return valorFinalOS;
    }

    public void setValorFinalOS(float valorFinalOS) {
        this.valorFinalOS = valorFinalOS;
    }

    public int getStatusOS() {
        return statusOS;
    }

    public void setStatusOS(int statusOS) {
        this.statusOS = statusOS;
    }
}
