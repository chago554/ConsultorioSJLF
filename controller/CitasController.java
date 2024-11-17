package com.utsem.consultorioSJLF.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsem.consultorioSJLF.Model.Cita;
import com.utsem.consultorioSJLF.repository.CitasRepo;

@RestController
@RequestMapping("citasSJLF")
public class CitasController {

	@Autowired
	CitasRepo citasRepo;

	// listar
	@PostMapping("listarSJLF")
	public List<Cita> postListar() {
		return citasRepo.findByOrderByFechaSJLFAsc();
	}

	// cargar cita
	@PostMapping("cargarCitaSJLF")
	public Cita postMethodName(@RequestBody Long id) {
		Optional<Cita> laCita = citasRepo.findById(id);
		if (laCita.isPresent()) {
			return laCita.get();
		}
		return new Cita();
	}

	// eliminar
	@PostMapping("eliminarSJLF")
	public String eliminarCita(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			try {
				citasRepo.delete(laCita.get());
				return "¡Se eliminó la cita exitosamente!";
			} catch (DataIntegrityViolationException e) {
				return "Este registro está conectado a otros datos, por lo que no se puede eliminar";
			}
		}
		return "¡No existe este médico!";
	}

	// Actualizar el estatus de la cita
	@PostMapping("cambiarEstatusSJLF")
	public String cambiarEstatusSJLF(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			laCita.get().setEstatus(cita.getEstatus());
			citasRepo.save(laCita.get());
			return "¡Estatus actualizado exitosamente!";
		}
		return "¡La cita no existe!";
	}

	// cambiar la fecha
	@PostMapping("cambiarFechaSJLF")
	public String cambiarFechaSJLF(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			laCita.get().setFechaSJLF(LocalDateTime.parse(cita.getNuevaFechaSJLF()));
			citasRepo.save(laCita.get());
			return "¡Fecha actualizada exitosamente!";
		}
		return "¡La cita no existe!";
	}

	// guardar cita
	@PostMapping("guadarSJLF")
	public String postGuadar(@RequestBody Cita cita) {

		if (cita.getNuevaFechaSJLF().contains(" ")) {
			return "¡No se puede guardar una fecha en blanco!";
		}

		cita.setFechaSJLF(LocalDateTime.parse(cita.getNuevaFechaSJLF()));
		citasRepo.save(cita);

		return "¡Cita creada exitosamente!";
	}
	
	//modificar paciente
	@PostMapping("modificarPacienteSJLF")
	public String cambiarPaciente(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			laCita.get().setPaciente(cita.getPaciente());
			citasRepo.save(laCita.get());
			return "¡Paciente actualizado correctamente!";
		}
		return "No hay una cita ";
	}
	
	
	// modificar medico
	@PostMapping("modificarMedicoSJLF")
	public String cambiarMedico(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			laCita.get().setMedico(cita.getMedico());
			citasRepo.save(laCita.get());
			return "¡Médico actulizado correctamente!";
		}
		return "No hay una cita ";
	}

}
