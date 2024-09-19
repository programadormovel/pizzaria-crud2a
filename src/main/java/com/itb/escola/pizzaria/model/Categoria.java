package com.itb.escola.pizzaria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
   // @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // nullable = false -> não permite valor null (NOT NULL)
    // lenfth -> tamanho "capacidade máxima"
    @Column(nullable = false, length = 45)
    private String nome;

    @Column(nullable = true, length = 100)
    private String descricao;

    private boolean codStatus;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    // Atributos de apoio

    // @Transient -> representa um atributo que NÃO CORRESPONDE A UMA COLUNA DA TABELA

    @Transient
    @JsonIgnore
    private String mensagemErro = "";
    @Transient
    @JsonIgnore
    private boolean isValid = true;

    public Categoria() {

    }

    public Categoria(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCodStatus() {
        return codStatus;
    }

    public void setCodStatus(boolean codStatus) {
        this.codStatus = codStatus;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean validarCategoria() {
        if(nome == null || nome.isEmpty()){
            mensagemErro += "O nome da categoria é obrigatório:";
            isValid = false;
        }
        return isValid;
    }

}
