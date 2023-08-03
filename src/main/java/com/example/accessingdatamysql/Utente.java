package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * La classe Utente rappresenta un oggetto che corrisponde ad una riga della tabella nel database.
 * Questa classe è annotata con @Entity, che indica a Hibernate di creare una tabella corrispondente
 * a questa classe nel database.
 */
@Entity
public class Utente {
    /**
     * Identificatore unico per l'utente, generato automaticamente dal database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Nome dell'utente.
     */
    private String name;

    /**
     * Indirizzo email dell'utente.
     */
    private String email;

    /**
     * Età dell'utente.
     */
    private Integer eta;

    /**
     * Restituisce l'identificatore unico dell'utente.
     *
     * @return L'identificatore unico dell'utente.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Imposta l'identificatore unico dell'utente.
     *
     * @param id L'identificatore unico dell'utente da impostare.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return Il nome dell'utente.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param name Il nome dell'utente da impostare.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return L'indirizzo email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     *
     * @param email L'indirizzo email dell'utente da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce l'età dell'utente.
     *
     * @return L'età dell'utente.
     */
    public int getEta() {
        return eta;
    }

    /**
     * Imposta l'età dell'utente.
     *
     * @param eta L'età dell'utente da impostare.
     */
    public void setEta(int eta) {
        this.eta = eta;
    }
}
