package com.thiago.fruitmanagementsystem.Model;

import com.thiago.fruitmanagementsystem.Enums.ClassificacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "frutas")
public class Fruta {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "O nome da fruta é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A classificação da fruta é obrigatória")
    private ClassificacaoEnum classificacao;

    @NotNull(message = "A fruta é fresca?")
    private Boolean fresca;

    @NotNull(message = "A quantidade disponível da fruta é obrigatória")
    private int qtdDisponivel;

    @NotNull(message = "O valor de venda da fruta é obrigatório")
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
}
