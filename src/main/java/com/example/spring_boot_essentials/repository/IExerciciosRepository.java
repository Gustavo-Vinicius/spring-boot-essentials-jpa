package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.ExerciciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IExerciciosRepository extends JpaRepository<ExerciciosEntity, Integer> {

}
