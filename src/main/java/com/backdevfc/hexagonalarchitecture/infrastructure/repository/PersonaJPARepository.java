package com.backdevfc.hexagonalarchitecture.infrastructure.repository;

import com.backdevfc.hexagonalarchitecture.infrastructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJPARepository extends JpaRepository<PersonaEntity, Long> {
}
