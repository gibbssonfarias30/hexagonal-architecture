package com.backdevfc.hexagonalarchitecture.domain.ports.in;

import com.backdevfc.hexagonalarchitecture.domain.model.Persona;

import java.util.Optional;

public interface PersonaIn {
    Persona crearPersona(Persona persona);
    Optional<Persona> getPersona(Long id);
    Optional<Persona> actualizarPersona(Long id, Persona persona);
    boolean eliminarPersona(Long id);
}
