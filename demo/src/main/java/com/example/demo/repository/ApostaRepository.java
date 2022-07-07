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
public interface ApostaRepository extends JpaRepository<Aposta, Integer> {
    @Query(value = "select p from Aposta p join fetch p.cliente c  join fetch c.user u where u.username = :username ")
    List<Aposta> findAllByAposta(@Param(value = "username") String username);

    List<Aposta> findAllBySorteio(Sorteio sorteio);

    Optional<Aposta> findById(Integer id);
}
