package com.example.spring_boot_essentials.service;

import com.example.spring_boot_essentials.dto.TreinoDto;
import com.example.spring_boot_essentials.exception.BadRequestException;
import com.example.spring_boot_essentials.exception.NotFoundException;
import com.example.spring_boot_essentials.model.AlunosEntity;
import com.example.spring_boot_essentials.model.ExerciciosEntity;
import com.example.spring_boot_essentials.model.TreinosEntity;
import com.example.spring_boot_essentials.repository.IAlunosRepository;
import com.example.spring_boot_essentials.repository.IExerciciosRepository;
import com.example.spring_boot_essentials.repository.ITreinosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {


    private final IExerciciosRepository exerciciosRepository;
    private final ITreinosRepository treinosRepository;
    private final IAlunosRepository alunosRepository;

    public void criarTreino(TreinoDto treinoDto) throws NotFoundException, BadRequestException {

        Set<ExerciciosEntity> exercicios = new HashSet<>();

        AlunosEntity aluno = alunosRepository.findByIdFetch(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno com id " + treinoDto.getAlunoId() + " não encontrado"));

        TreinosEntity treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId())
                .orElse(null);

        if(treino != null) {
            throw new BadRequestException("Treino com nome " + treinoDto.getNome() + " já existe para o aluno com id " + treinoDto.getAlunoId());
        }

        for (Integer exercicioId : treinoDto.getExerciciosIds()) {
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException("Exercicio com id " + exercicioId + " não encontrado"));

            exercicios.add(exercicio);

        }

        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
