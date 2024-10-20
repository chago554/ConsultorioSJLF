package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsem.consultorioSJLF.Model.Paciente;
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

		if (paciente.getNombreSJLF().isEmpty() || paciente.getCurpSJLF().isEmpty()) {
			return "¡Por favor, rellene los campos obligatorios!";
		}

		if (paciente.getCurpSJLF().length() < 18) {
			return "La Longitud de la CURP esta incompleta";
		}
	
		if (!Pattern.matches("[a-zA-Z0-9]+", paciente.getCurpSJLF().toString())) { // valida que no se ingresen caracteres especiales
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
	@PostMapping("eliminarSJLF")
	public String postEliminar(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findById(paciente.getId());
		if (elPaciente.isPresent()) {
			pacienteRepo.delete(elPaciente.get());
			return "¡Paciente eliminado con exito!";
		}
		return "Paciente no encontrado";
	}

	// modificar --> falta
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
		
			if (!Pattern.matches("[a-zA-Z0-9]+", paciente.getCurpSJLF().toString())) { // valida que no se ingresen caracteres especiales
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
