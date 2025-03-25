package com.thiago.fruitmanagementsystem.Repository;

import com.thiago.fruitmanagementsystem.Enums.ClassificacaoEnum;
import com.thiago.fruitmanagementsystem.Model.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FrutaRepository extends JpaRepository<Fruta, Long> {

    Optional<Fruta> findByNomeAndClassificacao(String nome, ClassificacaoEnum classificacao);

    List<Fruta> findAllByQtdDisponivelNotNull();

    @Query("SELECT f FROM Fruta f WHERE f.classificacao = :classificacao and f.fresca = :fresca order by f.valorVenda asc ")
    List<Fruta> findAllByClassificacaoAndFrescaAndOrderByValorVendaIdAsc(ClassificacaoEnum classificacao, Boolean fresca);

    @Query("SELECT f FROM Fruta f WHERE f.classificacao = :classificacao order by f.nome asc ")
    List<Fruta> findAllByClassificacaoAndOrderByNome(ClassificacaoEnum classificacao);

    @Query("SELECT f FROM Fruta f WHERE f.fresca = :fresca order by f.nome asc ")
    List<Fruta> findAllByFrescaAndOrderByNome(Boolean fresca);

    List<Fruta> findAllByNome(String nome);

    @Query("SELECT f FROM Fruta f order by f.valorVenda asc ")
    List<Fruta> findAllByValorVendaAsc();

    @Query("SELECT f FROM Fruta f order by f.valorVenda desc ")
    List<Fruta> findAllByValorVendaDesc();
}
