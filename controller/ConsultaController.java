package com.utsem.consultorioSJLF.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.utsem.consultorioSJLF.repository.ConsultaRepository;
@RestController
@RequestMapping("consultasSJLF")
public class ConsultaController {
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	


	

}
