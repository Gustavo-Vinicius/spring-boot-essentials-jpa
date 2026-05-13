package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.AlunosEntity;
import com.example.spring_boot_essentials.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {
    Optional<AlunosEntity> findByEmail(String email);

    @Query(value = "SELECT a FROM AlunosEntity a LEFT JOIN FETCH a.avaliacaoFisica WHERE a.id = :id")
    Optional<AlunosEntity> findByIdFetch(@Param("id") Integer id);
}
