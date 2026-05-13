package com.example.spring_boot_essentials.exception;

import lombok.*;

// @Getter
// Gera automaticamente os métodos getters
// Exemplo: getId(), getNome(), getPreco()
@Getter

// @Setter
// Gera automaticamente os métodos setters
// Exemplo: setId(), setNome(), setPreco()
@Setter

// @AllArgsConstructor
// Cria um construtor com todos os atributos da classe
// Exemplo:
// new ProdutoEntity(id, nome, preco, quantidade)
@AllArgsConstructor

// @NoArgsConstructor
// Cria um construtor vazio (sem parâmetros)
@NoArgsConstructor

// @ToString
// Gera automaticamente o método toString()
// Muito útil para debug e logs
@ToString

// @Builder
// Implementa o padrão Builder
// Permite criar objetos de forma fluida:
//
// ProdutoEntity.builder()
//      .id(1)
//      .nome("Notebook")
//      .build();
@Builder
public class ErroResponse {
    private String message;
    private Integer status;
}
