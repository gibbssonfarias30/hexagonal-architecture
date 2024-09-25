package com.backdevfc.hexagonalarchitecture.infrastructure.controller;


import com.backdevfc.hexagonalarchitecture.application.service.PersonaService;
import com.backdevfc.hexagonalarchitecture.domain.model.Persona;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/personas")
@RestController
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    public ResponseEntity<Persona> create(@RequestBody Persona persona) {
        Persona createPerso = personaService.crearPersona(persona);
        return new ResponseEntity<>(createPerso, HttpStatus.OK);
    }

    @GetMapping("/{personaId}")
    public ResponseEntity<Persona> getPersona(@PathVariable Long personaId) {
        return personaService.getPersona(personaId)
                .map(persona -> new ResponseEntity<>(persona, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{personaId}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long personaId, @RequestBody Persona persona) {
        return personaService.actualizarPersona(personaId, persona)
                .map(per -> new ResponseEntity<>(per, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{personaId}")
    public ResponseEntity<Persona> deletePersona(@PathVariable Long personaId) {
        if (personaService.eliminarPersona(personaId)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
