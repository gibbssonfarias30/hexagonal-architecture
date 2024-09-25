package com.backdevfc.hexagonalarchitecture.infrastructure.config;


import com.backdevfc.hexagonalarchitecture.application.service.PersonaService;
import com.backdevfc.hexagonalarchitecture.application.usecase.PersonaServiceImpl;
import com.backdevfc.hexagonalarchitecture.domain.ports.out.PersonaOut;
import com.backdevfc.hexagonalarchitecture.infrastructure.repository.PersonaJPARepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PersonaService personaService(PersonaOut personaOut) {
        return new PersonaService(new PersonaServiceImpl(personaOut));
    }

    @Bean
    public PersonaOut personaOut(PersonaJPARepositoryAdapter personaJPARepositoryAdapter) {
        return personaJPARepositoryAdapter;
    }
}
