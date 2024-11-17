package com.utsem.consultorioSJLF.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.utsem.consultorioSJLF.Model.Consulta;
import com.utsem.consultorioSJLF.repository.ConsultaRepository;

@RestController
@RequestMapping("consultasSJLF")
public class ConsultaController {

	@Autowired
	ConsultaRepository consultaRepository;

	// guardar
	@PostMapping("guadarSJLF")
	public String postMethodName(@RequestBody Consulta consulta) {
		
		try {
			
			if(consulta.getSintomasSJLF().isEmpty()) {
				return "¡Rellene todos los campos!";
			}
			
			consultaRepository.save(consulta);
			return "¡Cita creada exitosamente!";
			
		} catch (Exception e) {
			return "Error:" + e ;
		}
		
	
		
	

	}

}
