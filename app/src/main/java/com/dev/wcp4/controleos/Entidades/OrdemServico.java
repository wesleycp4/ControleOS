package com.dev.wcp4.controleos.Entidades;

import java.io.Serializable;
import java.util.Date;

public class OrdemServico implements Serializable {

    private int idOS;
    private String descricaoOS;
    private String equipamentosOS;
    private String dataAberturaOS;
    private String dataFechamentoOS;
    private String contato;
    private String contato2;
    private float valorOrcamentoOS;
    private float valorFinalOS;
    private int statusOS;
    private String usuarioNome;
    private String clienteNome;

    //, Date dataAberturaOS
    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS, int statusOS, String usuarioNome, String clienteNome ) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.dataAberturaOS = dataAberturaOS;
        this.statusOS = statusOS;
        this.usuarioNome = usuarioNome;
        this.clienteNome = clienteNome;
    }

    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS, String dataAberturaOS, int statusOS, String contato, String usuarioNome, String clienteNome) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.dataAberturaOS = dataAberturaOS;
        this.statusOS = statusOS;
        this.contato = contato;
        this.usuarioNome = usuarioNome;
        this.clienteNome = clienteNome;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
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

    public String getDataAberturaOS() {
        return dataAberturaOS;
    }

    public void setDataAberturaOS(String dataAberturaOS) {
        this.dataAberturaOS = dataAberturaOS;
    }

    public String getDataFechamentoOS() {
        return dataFechamentoOS;
    }

    public void setDataFechamentoOS(String dataFechamentoOS) {
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

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }
}