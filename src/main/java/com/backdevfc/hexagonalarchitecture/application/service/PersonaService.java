package com.backdevfc.hexagonalarchitecture.application.service;

import com.backdevfc.hexagonalarchitecture.domain.model.Persona;
import com.backdevfc.hexagonalarchitecture.domain.ports.in.PersonaIn;
import lombok.RequiredArgsConstructor;


import java.util.Optional;

@RequiredArgsConstructor
public class PersonaService implements PersonaIn {

    private final PersonaIn personaIn;

    @Override
    public Persona crearPersona(Persona persona) {
        return this.personaIn.crearPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return this.personaIn.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return this.personaIn.actualizarPersona(id, persona);
    }

    @Override
    public boolean eliminarPersona(Long id) {
        return this.personaIn.eliminarPersona(id);
    }
}
