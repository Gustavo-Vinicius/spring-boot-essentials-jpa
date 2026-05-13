package com.example.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExercicioDto {
    @NotBlank
    private String nome;
    private String grupoMuscular;
}
