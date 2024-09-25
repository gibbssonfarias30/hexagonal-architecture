package com.backdevfc.hexagonalarchitecture.infrastructure.repository;


import com.backdevfc.hexagonalarchitecture.domain.model.Persona;
import com.backdevfc.hexagonalarchitecture.domain.ports.out.PersonaOut;
import com.backdevfc.hexagonalarchitecture.infrastructure.entity.PersonaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PersonaJPARepositoryAdapter implements PersonaOut {

    private final PersonaJPARepository personaJPARepository;


    @Override
    public Persona createPersona(Persona persona) {
        PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
        return personaJPARepository.save(personaEntity).toDomainModel();
    }

    @Override
    public Optional<Persona> getPersona(Long id) {
        return personaJPARepository.findById(id)
                .map(PersonaEntity::toDomainModel);
    }

    @Override
    public Optional<Persona> updatePersona(Long id, Persona persona) {
        if (personaJPARepository.existsById(id)) {
            PersonaEntity personaEntity = PersonaEntity.fromDomainModel(persona);
            return Optional.of(personaJPARepository.save(personaEntity).toDomainModel());
        }
        return Optional.empty();
    }

    @Override
    public boolean deletePersona(Long id) {
        if(personaJPARepository.existsById(id)){
            personaJPARepository.deleteById(id);
        }
        return false;
    }
}
