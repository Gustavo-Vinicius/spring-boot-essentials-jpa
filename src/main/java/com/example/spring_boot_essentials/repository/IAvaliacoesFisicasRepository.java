package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

}
