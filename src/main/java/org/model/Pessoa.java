package org.model;
public abstract class Pessoa {
    private int id;
    private String nome;
    private String sexo;
    private int idade;
    private String cpf;
    private String telefone;

    public Pessoa(String nome, String sexo, int idade, String cpf, String telefone) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public Pessoa(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setIdade(int age) {
        this.idade = age;
    }
}
