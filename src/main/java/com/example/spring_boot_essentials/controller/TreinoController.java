package com.example.spring_boot_essentials.controller;

import com.example.spring_boot_essentials.dto.TreinoDto;
import com.example.spring_boot_essentials.exception.BadRequestException;
import com.example.spring_boot_essentials.exception.NotFoundException;
import com.example.spring_boot_essentials.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinoController {

    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTreino(@RequestBody TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        treinoService.criarTreino(treinoDto);
    }
}
