package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsem.consultorioSJLF.Model.Cita;
import com.utsem.consultorioSJLF.Model.Paciente;
import com.utsem.consultorioSJLF.repository.CitasRepo;
import com.utsem.consultorioSJLF.repository.PacienteRepo;

@RestController
@RequestMapping("pacientesSJLF")
public class PacienteController {

	@Autowired
	PacienteRepo pacienteRepo;

	// Consultar
	@PostMapping("listarSJLF")
	public List<Paciente> postListar() {
		return pacienteRepo.findAll();
	}

	// guardar
	@PostMapping("guardarSJLF")
	public String postCrear(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findByCurpSJLF(paciente.getCurpSJLF());
		
		if(elPaciente.isPresent()) {
			return "¡La curp ya existe!";
		}

		if (paciente.getNombreSJLF().isEmpty() || paciente.getCurpSJLF().isEmpty()) {
			return "¡Por favor, rellene los campos obligatorios!";
		}

		if (paciente.getCurpSJLF().length() < 18) {
			return "La Longitud de la CURP esta incompleta";
		}

		if (!Pattern.matches("[a-zA-Z0-9]+", paciente.getCurpSJLF().toString())) { // valida que no se ingresen
																					// caracteres especiales
			return "La CURP no debe de contener espacios en blanco o caracteres especiales";
		}

		pacienteRepo.save(paciente);
		return "¡Paciente guardado con exito!";

	}

	// consultar a un paciente especifico
	@PostMapping("consultarSJLF")
	public Paciente postMethodName(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findById(paciente.getId());
		if (elPaciente.isPresent()) {
			return elPaciente.get();
		}
		return new Paciente();
	}

	// Eliminar
	@Autowired
	CitasRepo citasRepo;

	@PostMapping("eliminarSJLF")
	public String postEliminar(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findById(paciente.getId());

		Long citas = citasRepo.countByPaciente(paciente);
		if (citas > 1) {
			return "No se puede eliminar a este usuario, tiene mas de una cita";
		}

		Optional<Cita> laCita = citasRepo.findByPaciente(paciente);
		if (elPaciente.isPresent()) {
			if (laCita.isPresent()) {
				return "No se puede eliminar este usuario, tiene una cita " + laCita.get().getEstatus();
			}
			pacienteRepo.delete(elPaciente.get());
			return "¡Se eliminó el paciente correctamente!";
		}
		return "No existe este usuario";
	}

	// modificar
	@PostMapping("modificarSJLF")
	public String postModificar(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findById(paciente.getId());
		Optional<Paciente> laCurp = pacienteRepo.findByCurpSJLF(paciente.getCurpSJLF());

		if (elPaciente.isPresent()) {

			if (paciente.getNombreSJLF().isEmpty() || paciente.getCurpSJLF().isEmpty()) {
				return "¡Por favor, rellene los campos obligatorios!";
			}

			if (paciente.getCurpSJLF().length() < 18) {
				return "La Longitud de la CURP esta incompleta";
			}

			if (!Pattern.matches("[a-zA-Z0-9]+", paciente.getCurpSJLF().toString())) { // valida que no se ingresen
																						// caracteres especiales
				return "La CURP no debe de contener espacios en blanco o caracteres especiales";
			}

			if (laCurp.isPresent() && laCurp.get().getId() != paciente.getId()) {
				return "La CURP ya existe";
			}

			elPaciente.get().setNombreSJLF(paciente.getNombreSJLF());
			elPaciente.get().setCurpSJLF(paciente.getCurpSJLF());
			elPaciente.get().setTipoSangre(paciente.getTipoSangre());
			pacienteRepo.save(elPaciente.get());
			return "¡Paciente actualizado exitosamente!";

		}
		return "El paciente NO existe";
	}
	
	
	
	
}
