package com.example.spring_boot_essentials.service;

import com.example.spring_boot_essentials.dto.AlunoDto;
import com.example.spring_boot_essentials.exception.BadRequestException;
import com.example.spring_boot_essentials.exception.NotFoundException;
import com.example.spring_boot_essentials.model.AlunosEntity;
import com.example.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import com.example.spring_boot_essentials.model.TreinosEntity;
import com.example.spring_boot_essentials.repository.IAlunosRepository;
import com.example.spring_boot_essentials.repository.IAvaliacoesFisicasRepository;
import com.example.spring_boot_essentials.repository.ITreinosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAluno(AlunoDto alunoDto) throws BadRequestException {
        AlunosEntity aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if(aluno != null) {
            throw new BadRequestException("Aluno com email " + alunoDto.getEmail() + " já existe");
        }

        alunosRepository.save(AlunosEntity.builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .build());
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
         AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno com id " + alunoId + " não encontrado"));

         AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacaoFisica();

         if(avaliacao == null) {
             throw new NotFoundException("Aluno com id " + alunoId + " não possui avaliação física");
         }

         return  avaliacao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarAluno(Integer alunoId) throws Exception {
        AlunosEntity aluno = alunosRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        //1. deletar treinos do aluno
        List<Integer> treinosAlunoIds = aluno.getTreinos().stream()
                .map(TreinosEntity::getId)
                .toList();

        treinosRepository.deleteAllById(treinosAlunoIds);

        //2. deletar o aluno
        alunosRepository.deleteById(alunoId);

        //3. deletar avaliação física
        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacaoFisica().getId());
    }
}
