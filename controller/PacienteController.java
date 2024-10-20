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
	
	//guardar
	@PostMapping("guardarSJLF")
	public String postCrear(@RequestBody Paciente paciente) {
		
		if(paciente.getCurpSJLF().length() < 18) {
			return "La Longitud de la CURP esta incompleta";
		}
		
		pacienteRepo.save(paciente);
		return "¡Paciente guardado con exito!";		
		
		
	
		
		
	}
	
	
	//consultar a un paciente especifico
	@PostMapping("consultarSJLF")
	public Paciente postMethodName(@RequestBody Paciente paciente) {
		Optional<Paciente> elPaciente = pacienteRepo.findById(paciente.getId());
		if(elPaciente.isPresent()) {
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
	
	
	//modificar --> falta

}
