package com.OuvertureAPP.ouverture.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@Entity

    public class Conta implements Serializable {

        private static final long serialVersionUID = 1L;

       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id ;

        @NotEmpty(message = "CPF Obrigatório")
        private String cpf;

        @NotEmpty(message = "Nome Obrigatório")
        private String nome;

        @NotEmpty(message = "Sobrenome Obrigatório")
        private String sobrenome;

        @NotEmpty(message = "Cidade Obrigatório")
        private String cidade;

        @NotEmpty(message = "CEP Obrigatório")
        private String cep;

        @NotEmpty(message = "Local Obrigatório")
        private String local;

        @NotEmpty(message = "Estado Obrigatório")
        private String estado;

        @NotEmpty(message = "Data de nascimento Obrigatório")
        private String data_de_nascimento;

    public Conta(Long id, String cpf, String nome, String sobrenome, String cidade, String cep, String local, String estado, String data_de_nascimento) {

        super();
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.local = local;
        this.data_de_nascimento = data_de_nascimento;

    }


    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }


        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSobrenome() {
            return sobrenome;
        }

        public void setSobrenome(String sobrenome) {
            this.sobrenome = sobrenome;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getData_de_nascimento() {
            return data_de_nascimento;
        }

        public void setData_de_nascimento(String data_de_nascimento) {
            this.data_de_nascimento = data_de_nascimento;
        }
    }





