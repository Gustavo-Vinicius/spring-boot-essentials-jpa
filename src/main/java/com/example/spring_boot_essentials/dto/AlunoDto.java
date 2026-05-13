package com.example.spring_boot_essentials.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    private String nome;
    private String email;
}
