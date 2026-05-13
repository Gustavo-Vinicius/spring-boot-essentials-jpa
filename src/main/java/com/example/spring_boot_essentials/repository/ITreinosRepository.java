package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ITreinosRepository extends JpaRepository<TreinosEntity, Integer> {
    Optional<TreinosEntity> findByNomeAndAlunoId(String nome, Integer alunoId);;
}
