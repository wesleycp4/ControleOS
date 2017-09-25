package com.dev.wcp4.controleos.Entidades;

public class Usuario {

    private String nome;
    private String email;
    private String contato1;
    private String contato2;
    private String usuario;
    private String senha;

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

}
