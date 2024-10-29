package com.utsem.consultorioSJLF.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utsem.consultorioSJLF.Model.Medico;

public interface MedicoRepo extends JpaRepository<Medico, Long>{

	Optional<Medico> findByCedulaSJLF(String cedulaSJLF);
}
