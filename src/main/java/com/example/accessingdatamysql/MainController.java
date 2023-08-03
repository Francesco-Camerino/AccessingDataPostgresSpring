package com.example.accessingdatamysql;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * La classe MainController è una classe di Spring che funge da controller per la gestione delle richieste HTTP.
 * È annotata con @Controller, il che significa che è un componente di Spring responsabile per la gestione delle richieste
 * e delle risposte HTTP.
 *
 * L'annotazione @RequestMapping(path="/demo") specifica che tutte le URL che iniziano con "/demo" saranno gestite da questo controller.
 * Ad esempio, "/demo/add" e "/demo/all" sono due URL gestite da questo controller.
 *
 * Questa classe utilizza l'interfaccia UtenteRepository (autowired) per interagire con il database e gestire i dati degli utenti.
 *
 * Il metodo addNewUser() è annotato con @PostMapping(path="/add"), il che significa che gestirà solo richieste HTTP POST
 * all'URL "/demo/add". Riceve due parametri, "name" e "email", dalla richiesta POST e crea un nuovo oggetto Utente
 * con i dati forniti. Quindi salva l'utente nel database utilizzando il metodo save() di UtenteRepository e restituisce una stringa "Saved".
 *
 * Il metodo getAllUsers() è annotato con @GetMapping(path="/all"), il che significa che gestirà solo richieste HTTP GET
 * all'URL "/demo/all". Questo metodo recupera tutti gli utenti dal database utilizzando il metodo findAll() di UtenteRepository
 * e restituisce un oggetto Iterable<Utente> che conterrà tutti gli utenti recuperati, convertiti in formato JSON o XML.
 *
 * @see org.springframework.stereotype.Controller
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see org.springframework.web.bind.annotation.PostMapping
 * @see org.springframework.web.bind.annotation.RequestParam
 * @see org.springframework.web.bind.annotation.ResponseBody
 * @see org.springframework.web.bind.annotation.GetMapping
 */
@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UtenteRepository utenteRepository;

    @GetMapping(path="/utenti")
    public @ResponseBody Iterable<Utente> getAllUsers() {
        // This returns a JSON or XML with the users
        return utenteRepository.findAll();
    }

    @PostMapping(path="/utente") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody Utente utente) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        utenteRepository.save(utente);
        return "Saved";
    }

    @GetMapping(path="/utenti/email/{email}")
    public @ResponseBody Utente findByEmail(@PathVariable String email) {
        // This returns a JSON or XML with the users
        return utenteRepository.findByEmail(email);
    }

    @GetMapping(path="/utenti/name/{name}")
    public @ResponseBody Iterable<Utente> findByName(@PathVariable String name) {
        // This returns a JSON or XML with the users
        return utenteRepository.findByName(name);
    }

    @GetMapping(path="/utenti/search/byNameContainingIgnoreCase/{name}")
    public @ResponseBody Iterable<Utente> findUtenteByNameContainingIgnoreCase(@PathVariable String name) {
        // This returns a JSON or XML with the users
        return utenteRepository.findUtenteByNameContainingIgnoreCase(name);
    }

    @GetMapping(path="/utente/{id}")
    public @ResponseBody Utente findById(@PathVariable Integer id) {
        // This returns a JSON or XML with the users
        // equivalente di
       /* Utente utente = utenteRepository.findById(id).orElse(null);
        if (utente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        return utente;*/
        return utenteRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));
    }
    @PatchMapping(path="/utente/{id}/patchEta")
    @Transactional
    public @ResponseBody int patchEta(@PathVariable Integer id, @RequestParam Integer eta) {
       return utenteRepository.patchEta(id,eta);
    }
}