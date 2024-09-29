package com.backdevfc.hexagonalarchitecture.infrastructure.repository;

import com.backdevfc.hexagonalarchitecture.domain.model.Persona;
import com.backdevfc.hexagonalarchitecture.infrastructure.entity.PersonaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonaJPARepositoryAdapterTest {

    @Mock
    PersonaJPARepository personaJPARepository;

    @InjectMocks
    PersonaJPARepositoryAdapter personaJPARepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personaJPARepositoryAdapter = new PersonaJPARepositoryAdapter(personaJPARepository);
    }


    @Test
    void createPersonaExitoso() {
        Persona persona = new Persona(1L, "Raul", "Mena", new Date(), "Masculino");
        PersonaEntity personaEntity = new PersonaEntity(1L, "Raul", "Mena", new Date(), "Masculino");

        Mockito.when(personaJPARepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        Persona personaAdapter = personaJPARepositoryAdapter.createPersona(persona);

        assertNotNull(personaAdapter);
        assertEquals(personaEntity.getId(), personaAdapter.getId());
    }

    @Test
    void findById_IsEmpty() {
        Long id = 1L;

        Mockito.when(personaJPARepository.findById(id)).thenReturn(Optional.empty());
        Optional<Persona> persona = personaJPARepositoryAdapter.getPersona(id);

        assertTrue(persona.isEmpty());
    }

    @Test
    void findById_ExistingId_ReturnsPersona() {
        Long id = 1L;
        PersonaEntity personaEntity = new PersonaEntity(1L, "Raul", "Mena", new Date(), "Masculino");

        Mockito.when(personaJPARepository.findById(id)).thenReturn(Optional.of(personaEntity));
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.getPersona(id);

        assertTrue(personaAdapter.isPresent());
        assertEquals(personaEntity.getId(), personaAdapter.get().getId());
    }

    @Test
    void update_ExistingIdAndValid_ReturnsUpdatedPersona() {
        Long id = 1L;
        Persona persona = new Persona(id, "Raul", "Gonzales", new Date(), "Masculino");
        PersonaEntity personaEntity = new PersonaEntity(id, "Raul", "Gonzales", new Date(), "Masculino");

        Mockito.when(personaJPARepository.existsById(id)).thenReturn(true);
        Mockito.when(personaJPARepository.save(Mockito.any(PersonaEntity.class))).thenReturn(personaEntity);
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.updatePersona(id, persona);

        assertTrue(personaAdapter.isPresent());
        assertEquals(persona.getApellido(), personaAdapter.orElse(null).getApellido());
    }

    @Test
    void update_NonExistingId_ReturnsEmptyOptional() {
        Long id = 1L;
        Persona persona = new Persona(id, "Raul", "Mena", new Date(), "Masculino");

        Mockito.when(personaJPARepository.findById(id)).thenReturn(Optional.empty());
        Optional<Persona> personaAdapter = personaJPARepositoryAdapter.updatePersona(id, persona);

        assertTrue(personaAdapter.isEmpty());
    }

    @Test
    void deleteById_NonExistingId_ReturnsFalse() {
        Long id = 1L;

        Mockito.when(personaJPARepository.existsById(id)).thenReturn(false);
        boolean personaAdapter = personaJPARepositoryAdapter.deletePersona(id);

        assertFalse(personaAdapter);
    }

    @Test
    void deleteById_ExistingId_ReturnsTrue() {
        Long id = 1L;

        Mockito.when(personaJPARepository.existsById(id)).thenReturn(true);
        boolean personaAdapter = personaJPARepositoryAdapter.deletePersona(id);

        assertTrue(personaAdapter);
    }
}
