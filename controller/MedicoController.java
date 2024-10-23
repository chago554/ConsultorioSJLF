package com.utsem.consultorioSJLF.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utsem.consultorioSJLF.Model.Medico;
import com.utsem.consultorioSJLF.repository.MedicoRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


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
	
	
	
	}
