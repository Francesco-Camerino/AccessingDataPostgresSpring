package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.Utente;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UtenteRepository extends CrudRepository<Utente,Integer> {
    Utente findByEmail(String email);
    List<Utente> findByName(String nome);
    List<Utente> findUtenteByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("UPDATE Utente u SET u.eta = :eta WHERE u.id = :id")
    int patchEta(@Param("id") Integer id, @Param("eta") Integer eta);
}