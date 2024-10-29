package com.utsem.consultorioSJLF.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsem.consultorioSJLF.Model.Cita;
import com.utsem.consultorioSJLF.Model.Medico;
import com.utsem.consultorioSJLF.Model.Paciente;
import com.utsem.consultorioSJLF.repository.CitasRepo;
import com.utsem.consultorioSJLF.repository.MedicoRepo;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("medicosSJLF")
public class MedicoController {

	@Autowired
	MedicoRepo medicoRepo;
	
	
	//consultar
	@PostMapping("listarSJLF")
	public List<Medico> listarMedicos() {
		return medicoRepo.findAll();
	}
	
	// guardar
	@PostMapping("guardarSJLF")
	public String postCrear(@RequestBody Medico medico) {

		if (medico.getCedulaSJLF().isEmpty() || medico.getNombreSJLF().isEmpty() || medico.getApellidosSJLF().isEmpty() ) {
			return "¡Por favor, rellene los campos obligatorios!";
		}
		if(medico.getCedulaSJLF().length()< 8 ) {
			return "La cédula profesional no cumple con la longitud mínima";
		}
		medicoRepo.save(medico);
		return "¡Médico guardado con exito!";

	}
	
	// consultar a un medico especifico
		@PostMapping("consultarSJLF")
		public Medico postMethodName(@RequestBody Medico medico) {
			Optional<Medico> elMedico = medicoRepo.findById(medico.getId());
			if (elMedico.isPresent()) {
				return elMedico.get();
			}
			return new Medico();
		}
	
	// Eliminar
		@Autowired
		CitasRepo citasRepo;

		@PostMapping("eliminarSJLF")
		public String postEliminar(@RequestBody Medico medico) {
			Optional<Medico> elMedico = medicoRepo.findById(medico.getId());

			Long citas = citasRepo.countByMedico(medico);
			if (citas >=1) {
				return "¡No se puede eliminar a este médico!";
			}
			if (elMedico.isPresent()) {
				medicoRepo.delete(elMedico.get());
				return "¡Se eliminó el médico correctamente!";
			}
			return "¡No existe este médico!";
		}

		// modificar
		@PostMapping("modificarSJLF")
		public String postModificar(@RequestBody Medico medico) {

			Optional<Medico> elMedico = medicoRepo.findById(medico.getId());
			Optional<Medico> laCedula = medicoRepo.findByCedulaSJLF(medico.getCedulaSJLF());

			if (elMedico.isPresent()) {

				if (medico.getNombreSJLF().isEmpty() || medico.getCedulaSJLF().isEmpty()
						|| medico.getApellidosSJLF().isEmpty()) {
					return "¡Por favor, rellene los campos obligatorios!";
				}

				if (medico.getCedulaSJLF().length() < 8) {
					return "La longitud de la cédula esta incompleta";
				}

				if (laCedula.isPresent() && laCedula.get().getId() != medico.getId()) {
					return "El médico ya existe";
				}

				elMedico.get().setNombreSJLF(medico.getNombreSJLF());
				elMedico.get().setCedulaSJLF(medico.getCedulaSJLF());
				elMedico.get().setApellidosSJLF(medico.getApellidosSJLF());

				medicoRepo.save(elMedico.get());
				return "¡Médico actualizado exitosamente!";

			}
			return "El médico NO existe";
		}

		
	}
