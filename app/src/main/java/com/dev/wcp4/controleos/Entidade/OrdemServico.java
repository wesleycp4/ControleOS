package com.dev.wcp4.controleos.Entidade;

import java.io.Serializable;

public class OrdemServico implements Serializable {

    private int idOS;
    private String descricaoOS;
    private String equipamentosOS;
    private String dataAberturaOS;
    private String dataFechamentoOS;
    private String contato;//*
    private String contato2;//*
    private int statusOS;
    private String usuarioNome; //nome usuario sistema
    private String clienteNome;//*
    private String clienteCpf;//*
    private String clienteEmail;//*
    private String clienteRua;//*
    private int clienteNumero;//*
    private String clienteComplemento;//*
    private String clienteBairro;//*
    private String clienteCidade;//*
    private String clienteEstado;//*
    private String clienteCep;//*
    private String imagem;

    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS,String imagem, String dataAberturaOS, String dataFechamentoOS, int statusOS) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.imagem = imagem;
        this.dataAberturaOS = dataAberturaOS;
        this.dataFechamentoOS = dataFechamentoOS;
        this.statusOS = statusOS;
    }

    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS, String dataAberturaOS, String contato, String contato2, int statusOS, String usuarioNome, String clienteNome, String clienteEmail, String clienteRua, int clienteNumero, String clienteComplemento, String clienteBairro, String clienteCidade, String clienteEstado, String clienteCep) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.dataAberturaOS = dataAberturaOS;
        this.contato = contato;
        this.contato2 = contato2;
        this.statusOS = statusOS;
        this.usuarioNome = usuarioNome;
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.clienteRua = clienteRua;
        this.clienteNumero = clienteNumero;
        this.clienteComplemento = clienteComplemento;
        this.clienteBairro = clienteBairro;
        this.clienteCidade = clienteCidade;
        this.clienteEstado = clienteEstado;
        this.clienteCep = clienteCep;
    }

    public OrdemServico(int idOS, String descricaoOS, String equipamentosOS, String dataAberturaOS, String dataFechamentoOS, String contato, String contato2, int statusOS, String usuarioNome, String clienteNome, String clienteCpf, String clienteEmail, String clienteRua, int clienteNumero, String clienteComplemento, String clienteBairro, String clienteCidade, String clienteEstado, String clienteCep, String imagem) {
        this.idOS = idOS;
        this.descricaoOS = descricaoOS;
        this.equipamentosOS = equipamentosOS;
        this.dataAberturaOS = dataAberturaOS;
        this.dataFechamentoOS = dataFechamentoOS;
        this.contato = contato;
        this.contato2 = contato2;
        this.statusOS = statusOS;
        this.usuarioNome = usuarioNome;
        this.clienteNome = clienteNome;
        this.clienteCpf = clienteCpf;
        this.clienteEmail = clienteEmail;
        this.clienteRua = clienteRua;
        this.clienteNumero = clienteNumero;
        this.clienteComplemento = clienteComplemento;
        this.clienteBairro = clienteBairro;
        this.clienteCidade = clienteCidade;
        this.clienteEstado = clienteEstado;
        this.clienteCep = clienteCep;
        this.imagem = imagem;
    }

    public OrdemServico(int idOS) {
        this.idOS = idOS;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteRua() {
        return clienteRua;
    }

    public void setClienteRua(String clienteRua) {
        this.clienteRua = clienteRua;
    }

    public int getClienteNumero() {
        return clienteNumero;
    }

    public void setClienteNumero(int clienteNumero) {
        this.clienteNumero = clienteNumero;
    }

    public String getClienteComplemento() {
        return clienteComplemento;
    }

    public void setClienteComplemento(String clienteComplemento) {
        this.clienteComplemento = clienteComplemento;
    }

    public String getClienteBairro() {
        return clienteBairro;
    }

    public void setClienteBairro(String clienteBairro) {
        this.clienteBairro = clienteBairro;
    }

    public String getClienteCidade() {
        return clienteCidade;
    }

    public void setClienteCidade(String clienteCidade) {
        this.clienteCidade = clienteCidade;
    }

    public String getClienteEstado() {
        return clienteEstado;
    }

    public void setClienteEstado(String clienteEstado) {
        this.clienteEstado = clienteEstado;
    }

    public String getClienteCep() {
        return clienteCep;
    }

    public void setClienteCep(String clienteCep) {
        this.clienteCep = clienteCep;
    }
}