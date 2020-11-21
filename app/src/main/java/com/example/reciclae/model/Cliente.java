package com.example.reciclae.model;

import androidx.room.Entity;

@Entity
public class Cliente {

    public Cliente(String bairro, String cep, String cidade, String complemento, String documento, String estado, String nome, String numero, String rua, String telefone) {
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.complemento = complemento;
        this.documento = documento;
        this.estado = estado;
        this.nome = nome;
        this.numero = numero;
        this.rua = rua;
        this.telefone = telefone;
    }

    public Cliente() {
    }

    public String bairro;
    public String cep;
    public String cidade;
    public String complemento;
    public String documento;
    public String estado;
    public String nome;
    public String numero;
    public String rua;
    public String telefone;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
