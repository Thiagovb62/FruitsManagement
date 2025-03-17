package com.thiago.fruitmanagementsystem.Repository;

import com.thiago.fruitmanagementsystem.Model.Fruta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrutaRepository extends JpaRepository<Fruta, Long> {
}
