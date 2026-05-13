package com.example.spring_boot_essentials.repository;

import com.example.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import com.example.spring_boot_essentials.model.AvaliacoesFisicasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;


public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

    @NativeQuery(value = """
    SELECT a.id                              idAluno,
           a.nome                            nomeAluno,
           af.id                             idAvaliacao,
           af.peso                           peso,
           af.altura                         altura,
           af.porcentagem_gordura_corporal  percentualGorduraCorporal
    FROM avaliacoes_fisicas af
    INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
    """)
    List<AvaliacoesFisicasProjection> getAllAvaliacoes();

    @NativeQuery(value = """
        SELECT a.id                             idAluno,
               a.nome                           nomeAluno,
               af.id                            idAvaliacao, 
               af.peso                          peso,
               af.altura                        altura,
               af.porcentagem_gordura_corporal  percentualGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
        """,
            countQuery = """
        SELECT count(af.id)
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
        ON a.avaliacao_fisica_id = af.id
        """)
    Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Pageable pageable);

}
