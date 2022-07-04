package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

    @Query(value = "select s from Sorteio s where s.enabled = true")
    List<Sorteio> findByEnableTrue();

    List<Sorteio> findAllByOrderByIdDesc();
}
