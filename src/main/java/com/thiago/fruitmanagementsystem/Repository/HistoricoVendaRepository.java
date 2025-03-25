package com.thiago.fruitmanagementsystem.Repository;

import com.thiago.fruitmanagementsystem.Model.HistoricoVendaFrutas;
import com.thiago.fruitmanagementsystem.Model.HistoricoVendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoVendaRepository extends JpaRepository<HistoricoVendas, Long> {

    @Query("select distinct hf from HistoricoVendas hv join HistoricoVendaFrutas hf on hv.id = hf.historicoVendas.id join Fruta f on hf.fruta.id = f.id")
    List<HistoricoVendaFrutas> findAllHstoricos();

}
