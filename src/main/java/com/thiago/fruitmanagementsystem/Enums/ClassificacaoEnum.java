package com.thiago.fruitmanagementsystem.Enums;

public enum ClassificacaoEnum {
    EXTRA(1, "Extra"),
    DE_PRIMEIRA(2, "De Primeira"),
    DE_SEGUNDA(3, "De Segunda"),
    DE_TERCEIRA(4, "De Terceira");

    private final int valor;
    private final String descricao;

    ClassificacaoEnum(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ClassificacaoEnum fromValor(int valor) {
        for (ClassificacaoEnum classificacao : ClassificacaoEnum.values()) {
            if (classificacao.getValor() == valor) {
                return classificacao;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + valor);
    }

    public static ClassificacaoEnum fromDescricao(String descricao) {
        for (ClassificacaoEnum classificacao : ClassificacaoEnum.values()) {
            if (classificacao.getDescricao().equalsIgnoreCase(descricao)) {
                return classificacao;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
