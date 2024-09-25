package com.backdevfc.hexagonalarchitecture.application.usecase;

import com.backdevfc.hexagonalarchitecture.domain.model.Persona;
import com.backdevfc.hexagonalarchitecture.domain.ports.in.PersonaIn;
import com.backdevfc.hexagonalarchitecture.domain.ports.out.PersonaOut;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaIn {

    private final PersonaOut personaOut;

    @Override
    public Persona crearPersona(Persona persona) {
        return personaOut.createPersona(persona);
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaOut.getPersona(id);
    }

    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        return personaOut.updatePersona(id, persona);
    }

    @Override
    public boolean eliminarPersona(Long id) {
        return personaOut.deletePersona(id);
    }
}
