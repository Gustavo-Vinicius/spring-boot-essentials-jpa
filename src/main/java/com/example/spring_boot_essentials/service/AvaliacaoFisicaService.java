package com.example.spring_boot_essentials.service;

import com.example.spring_boot_essentials.dto.AvaliacaoFisicaDto;
import com.example.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import com.example.spring_boot_essentials.exception.BadRequestException;
import com.example.spring_boot_essentials.exception.NotFoundException;
import com.example.spring_boot_essentials.model.AlunosEntity;
import com.example.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import com.example.spring_boot_essentials.repository.IAlunosRepository;
import com.example.spring_boot_essentials.repository.IAvaliacoesFisicasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

   private final IAlunosRepository alunosRepository;
   private  final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

   public void criarAvaliacaoFisica(AvaliacaoFisicaDto avaliacaoFisicaDto) throws BadRequestException, NotFoundException {
       AlunosEntity aluno = alunosRepository.findById(avaliacaoFisicaDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

       AvaliacoesFisicasEntity avaliacoesFisica = aluno.getAvaliacaoFisica();
       if(avaliacoesFisica != null ) {
           throw new BadRequestException("Aluno já possui uma avaliação física");
       }

       avaliacoesFisica = AvaliacoesFisicasEntity.builder()
               .peso(avaliacaoFisicaDto.getPeso())
               .altura(avaliacaoFisicaDto.getAltura())
               .porcentagemGorduraCorporal(avaliacaoFisicaDto.getPorcentagemGorduraCorporal())
               .build();


       aluno.setAvaliacaoFisica(avaliacoesFisica);
       alunosRepository.save(aluno);
   }

   public List<AvaliacoesFisicasProjection> getAllAvaliacoes() {
       return avaliacoesFisicasRepository.getAllAvaliacoes();
   }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Integer page, Integer size) {
        return avaliacoesFisicasRepository.getAllAvaliacoesPageable(PageRequest.of(page, size));
    }
}
