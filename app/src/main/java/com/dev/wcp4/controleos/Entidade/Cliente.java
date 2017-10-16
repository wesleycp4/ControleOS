package com.dev.wcp4.controleos.Entidade;

public class Cliente {

    private int idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String contatoCliente;
    private String contato2Cliente;
    private String ruaCliente;
    private int numeroCliente;
    private String complementoCliente;
    private String bairroCliente;
    private String cidadeCliente;
    private String cepCliente;
    private String estadoCliente;

    public Cliente() {
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(int idCliente, String nomeCliente, String contatoCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.contatoCliente = contatoCliente;
    }

    public Cliente(int idCliente, String nomeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
    }

    public Cliente(int idCliente, String nomeCliente, String emailCliente, String cpfCliente, String contatoCliente, String contato2Cliente, String ruaCliente, int numeroCliente, String complementoCliente, String bairroCliente, String cidadeCliente, String cepCliente, String estadoCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.cpfCliente = cpfCliente;
        this.contatoCliente = contatoCliente;
        this.contato2Cliente = contato2Cliente;
        this.ruaCliente = ruaCliente;
        this.numeroCliente = numeroCliente;
        this.complementoCliente = complementoCliente;
        this.bairroCliente = bairroCliente;
        this.cidadeCliente = cidadeCliente;
        this.cepCliente = cepCliente;
        this.estadoCliente = estadoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getContatoCliente() {
        return contatoCliente;
    }

    public void setContatoCliente(String contatoCliente) {
        this.contatoCliente = contatoCliente;
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

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getComplementoCliente() {
        return complementoCliente;
    }

    public void setComplementoCliente(String complementoCliente) {
        this.complementoCliente = complementoCliente;
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

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    @Override
    public String toString() {
        return getNomeCliente();
    }
}
