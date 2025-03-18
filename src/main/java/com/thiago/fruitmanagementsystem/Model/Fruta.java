package com.thiago.fruitmanagementsystem.Model;

import com.thiago.fruitmanagementsystem.Enums.ClassificacaoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "frutas")
public class Fruta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private ClassificacaoEnum classificacao;

    private Boolean fresca;

    private int qtdDisponivel;

    private double valorVenda;


    public Fruta() {
    }

    public Fruta(String nome, ClassificacaoEnum classificacao, Boolean fresca, int qtdDisponivel, double valorVenda) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.fresca = fresca;
        this.qtdDisponivel = qtdDisponivel;
        this.valorVenda = valorVenda;
    }

    public Fruta(FrutaRequestDTO frutaRequestDTO) {
        this.nome = frutaRequestDTO.nome();
        this.classificacao = ClassificacaoEnum.fromValor(frutaRequestDTO.classificacao());
        this.fresca = frutaRequestDTO.fresca();
        this.qtdDisponivel = frutaRequestDTO.qtdDisponivel();
        this.valorVenda = frutaRequestDTO.valorVenda();
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

    public ClassificacaoEnum getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoEnum classificacao) {
        this.classificacao = classificacao;
    }

    public Boolean getFresca() {
        return fresca;
    }

    public void setFresca(Boolean fresca) {
        this.fresca = fresca;
    }


    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return "Fruta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", classificacao=" + classificacao +
                ", fresca=" + fresca +
                ", qtdDisponivel=" + qtdDisponivel +
                ", valorVenda=" + valorVenda +
                '}';
    }
}
