package com.dev.wcp4.controleos.Entidade;

public class Usuario {

    private int idfuncionario;
    private String nome;
    private String email;
    private String contato1;
    private String contato2;
    private String usuario;
    private String senha;
    private int adm;

    public Usuario() {
    }

    public Usuario(String usuario, String senha) {
        super();
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String contato1, String contato2, String usuario, String senha, int genero) {
        this.nome = nome;
        this.email = email;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(int idfuncionario, String nome, String email, String usuario, int adm) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.email = email;
        this.usuario = usuario;
        this.adm = adm;
    }

    public Usuario(int idfuncionario, String nome, String email, String contato1, String contato2, String usuario, String senha, int adm) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.email = email;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.usuario = usuario;
        this.senha = senha;
        this.adm = adm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato1() {
        return contato1;
    }

    public void setContato1(String contato1) {
        this.contato1 = contato1;
    }

    public String getContato2() {
        return contato2;
    }

    public void setContato2(String contato2) {
        this.contato2 = contato2;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public int getAdm() {
        return adm;
    }

    public void setAdm(int adm) {
        this.adm = adm;
    }
}
