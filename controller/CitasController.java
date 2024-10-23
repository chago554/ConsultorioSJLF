package com.utsem.consultorioSJLF.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
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
		return citasRepo.findAll();
	}

	//
	@PostMapping("cambiarEstatusSJLF")
	public String cambiarEstatusSJLF(@RequestBody Cita cita) {
		Optional<Cita> laCita = citasRepo.findById(cita.getId());
		if (laCita.isPresent()) {
			laCita.get().setEstatus(cita.getEstatus());
			citasRepo.save(laCita.get());
			return "Estatus actualizado";
		}

		return "La cita no existe";
	}

}
