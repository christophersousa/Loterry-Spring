package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Aposta;
import com.example.demo.model.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

    @Query(value = "select s from Sorteio s where s.enabled = true")
    List<Sorteio> findByEnableTrue();

    List<Sorteio> findAllByOrderByIdDesc();

    Optional<Sorteio> findById(Integer id);

    @Query(value = "select a from Sorteio s join fetch Aposta a where s.id =: id")
    List<Aposta> findAllByApostas(@Param("id") Integer id);
}
