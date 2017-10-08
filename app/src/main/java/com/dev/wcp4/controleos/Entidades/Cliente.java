package com.dev.wcp4.controleos.Entidades;

public class Cliente {

    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String contato1Cliente;
    private String contato2Cliente;
    private String ruaCliente;
    private String numeroCliente;
    private String bairroCliente;
    private String cidadeCliente;
    private String cepCliente;

    public Cliente() {
    }

    public Cliente(String nomeCliente, String contato1Cliente) {
        this.nomeCliente = nomeCliente;
        this.contato1Cliente = contato1Cliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getContato1Cliente() {
        return contato1Cliente;
    }

    public void setContato1Cliente(String contato1Cliente) {
        this.contato1Cliente = contato1Cliente;
    }

    public String getContato2Cliente() {
        return contato2Cliente;
    }

    public void setContato2Cliente(String contato2Cliente) {
        this.contato2Cliente = contato2Cliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }

    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

}
