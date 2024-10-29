package com.utsem.consultorioSJLF.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.utsem.consultorioSJLF.Model.Cita;
import com.utsem.consultorioSJLF.Model.Medico;
import com.utsem.consultorioSJLF.Model.Paciente;




public interface CitasRepo extends JpaRepository<Cita, Long> {

	Optional<Cita> findByPaciente(Paciente paciente);
	Long countByPaciente(Paciente paciente);
	Optional<Cita> findByMedico(Medico medico);
	Long countByMedico(Medico medico);
	
}
	