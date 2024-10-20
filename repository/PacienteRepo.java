package com.utsem.consultorioSJLF.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.consultorioSJLF.Model.Paciente;

public interface PacienteRepo extends JpaRepository<Paciente, Long> {

	Optional<Paciente> findByCurpSJLF (String curp);
	
}
