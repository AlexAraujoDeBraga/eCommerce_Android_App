package com.leklab.alex.lojabraga.Model;

public class Users {
    private String nome;
    private String phone;
    private String email;
    private String senha;

    public Users(){

    }

    public Users(String nome, String phone, String email, String senha) {
        this.nome = nome;
        this.phone = phone;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
