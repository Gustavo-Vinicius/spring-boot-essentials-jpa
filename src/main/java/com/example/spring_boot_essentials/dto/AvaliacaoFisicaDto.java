package com.example.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoFisicaDto {

    @NotNull
   private Integer alunoId;
    @NotNull
   private BigDecimal peso;
    @NotNull
   private BigDecimal altura;
    @NotNull
   private BigDecimal porcentagemGorduraCorporal;
}
