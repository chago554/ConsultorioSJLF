package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	
	//filtrar
	@PostMapping("filtrarPacienteSJLF")
	public List<Paciente> postFiltrar(@RequestBody String filtro) {
		//return pacienteRepo.findByNombreSJLFOrCurpSJLFContains(filtro, filtro);  //esta es la manera de filtrar por nombre o curp
		return pacienteRepo.findByNombreSJLFContains(filtro);
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

		if (elPaciente.isPresent()) {
			try {
				pacienteRepo.delete(elPaciente.get());
				return "¡Se eliminó el paciente correctamente!";
			} catch (DataIntegrityViolationException e) {
				return "Este registro está conectado a otros datos, por lo que no se puede eliminar";
			}
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
