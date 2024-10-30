package com.utsem.consultorioSJLF.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.consultorioSJLF.Model.Enfermero;

public interface EnfermeroRepo extends JpaRepository<Enfermero, Long> {

	Optional<Enfermero> findByCurpSJLF(String curpSJLF);

}
