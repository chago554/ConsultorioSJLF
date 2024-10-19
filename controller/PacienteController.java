package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;

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

}
