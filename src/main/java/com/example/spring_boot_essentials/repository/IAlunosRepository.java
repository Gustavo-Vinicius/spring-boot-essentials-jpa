package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.AlunosEntity;
import com.example.spring_boot_essentials.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

}
