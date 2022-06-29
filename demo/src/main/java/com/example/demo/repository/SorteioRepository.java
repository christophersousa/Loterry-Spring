package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sorteio;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Integer> {

}
