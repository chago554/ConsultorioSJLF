package com.utsem.consultorioSJLF.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utsem.consultorioSJLF.Model.Paciente;

public interface PacienteRepo extends JpaRepository<Paciente, Long> {
	Optional<Paciente> findByCurpSJLF (String curp);

	//List<Paciente> findByNombreSJLFOrCurpSJLFContains(String nombreSJLF, String curpSJLF);  //esta linea es para buscar por nombre o curp, pero yo solo voy a filtrar por nombre. Por qué? porque si
	List<Paciente> findByNombreSJLFContains(String nombreSJLF);

}
