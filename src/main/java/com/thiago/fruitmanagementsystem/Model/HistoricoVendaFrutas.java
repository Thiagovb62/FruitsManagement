package com.thiago.fruitmanagementsystem.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "historico_venda_fruta")
public class HistoricoVendaFrutas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "historico_venda_id")
    private HistoricoVendas historicoVendas;


    @ManyToOne
    @JoinColumn(name = "fruta_id")
    private Fruta fruta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HistoricoVendas getHistoricoVendas() {
        return historicoVendas;
    }

    public void setHistoricoVendas(HistoricoVendas historicoVendas) {
        this.historicoVendas = historicoVendas;
    }

    public Fruta getFruta() {
        return fruta;
    }

    public void setFruta(Fruta fruta) {
        this.fruta = fruta;
    }
}