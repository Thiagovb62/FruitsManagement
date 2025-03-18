package com.thiago.fruitmanagementsystem.Repository;

import com.thiago.fruitmanagementsystem.Model.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FrutaRepository extends JpaRepository<Fruta, Long> {

    List<Fruta> findAllByQtdDisponivelNotNull();

    List<Fruta> findAllByClassificacao(String classificacao);

    List<Fruta> findAllByFresca(Boolean fresca);

    Optional<Fruta> findByNome(String nome);

    @Query("SELECT f FROM Fruta f order by f.valorVenda asc ")
    List<Fruta> findAllByValorVendaAsc();

    @Query("SELECT f FROM Fruta f order by f.valorVenda desc ")
    List<Fruta> findAllByValorVendaDesc();
}
