package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.ExerciciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IExerciciosRepository extends JpaRepository<ExerciciosEntity, Integer> {
    List<ExerciciosEntity> findAllByGrupoMuscular(String grupoMuscular);
}
