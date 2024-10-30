package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utsem.consultorioSJLF.Model.Enfermero;
import com.utsem.consultorioSJLF.repository.EnfermeroRepo;

@RestController
@RequestMapping("enfermerosSJLF")
public class EnfermeroController {

	@Autowired
	EnfermeroRepo enfermeroRepo;
	
	//listar
	@PostMapping("listarSJLF")
	public List<Enfermero> listar () {
		return enfermeroRepo.findAll();
	}

	// guardar
	@PostMapping("guardarSJLF")
	public String postCrear(@RequestBody Enfermero enfermero) {
		Optional<Enfermero> elEnfermero = enfermeroRepo.findByCurpSJLF(enfermero.getCurpSJLF());
		
		if(elEnfermero.isPresent()) {
			return "¡La curp ya existe!";
		}

		if (enfermero.getNombreSJLF().isEmpty() || enfermero.getCurpSJLF().isEmpty()) {
			return "¡Por favor, rellene los campos obligatorios!";
		}

		if (enfermero.getCurpSJLF().length() < 18) {
			return "La Longitud de la CURP esta incompleta";
		}

		if (!Pattern.matches("[a-zA-Z0-9]+", enfermero.getCurpSJLF().toString())) { // valida que no se ingresen
																					// caracteres especiales
			return "La CURP no debe de contener espacios en blanco o caracteres especiales";
		}

		enfermeroRepo.save(enfermero);
		return "¡Enfermero guardado con exito!";

	}
	
	// consultar a un enfermero especifico
		@PostMapping("consultarSJLF")
		public Enfermero postMethodName(@RequestBody Enfermero enfermero) {
			Optional<Enfermero> elEnfermero = enfermeroRepo.findById(enfermero.getId());
			if (elEnfermero.isPresent()) {
				return elEnfermero.get();
			}
			return new Enfermero();
		}
	

		//eliminar
		@PostMapping("eliminarSJLF")
		public String postEliminar(@RequestBody Enfermero enfermero) {
			Optional<Enfermero> elEnfermero = enfermeroRepo.findById(enfermero.getId());	
			if (elEnfermero.isPresent()) {
				enfermeroRepo.deleteById(enfermero.getId());
				return "¡Se eliminó el enfermero correctamente!";
			}
			return "No existe este enfermero";
		}
	

		// modificar
		@PostMapping("modificarSJLF")
		public String postModificar(@RequestBody Enfermero enfermero) {
			Optional<Enfermero> elEnfermero = enfermeroRepo.findById(enfermero.getId());
			Optional<Enfermero> laCurp = enfermeroRepo.findByCurpSJLF(enfermero.getCurpSJLF());

			if (elEnfermero.isPresent()) {
				if (enfermero.getNombreSJLF().isEmpty() || enfermero.getCurpSJLF().isEmpty()) {
					return "¡Por favor, rellene los campos obligatorios!";
				}
				if (enfermero.getCurpSJLF().length() < 18) {
					return "La Longitud de la CURP esta incompleta";
				}
				if (!Pattern.matches("[a-zA-Z0-9]+", enfermero.getCurpSJLF().toString())) { // valida que no se ingresen																	// caracteres especiales
					return "La CURP no debe de contener espacios en blanco o caracteres especiales";
				}
				if (laCurp.isPresent() && laCurp.get().getId() != enfermero.getId()) {
					return "La CURP ya existe";
				}
				elEnfermero.get().setNombreSJLF(enfermero.getNombreSJLF());
				elEnfermero.get().setCurpSJLF(enfermero.getCurpSJLF());
				enfermeroRepo.save(elEnfermero.get());
				return "¡Enfermero actualizado exitosamente!";

			}
			return "El enfermero NO existe";
		}
		
		
	
}
