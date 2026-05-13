package com.example.spring_boot_essentials.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="exercicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExerciciosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(name="grupo_muscular", nullable = false)
    private String grupoMuscular;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunosEntity aluno;
}
