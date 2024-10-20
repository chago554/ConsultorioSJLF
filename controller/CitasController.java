package com.utsem.consultorioSJLF.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("listarSJLF")
	public List<Cita> postListar() {
		return citasRepo.findAll();
	}
	
}
