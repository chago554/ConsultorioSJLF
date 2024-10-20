package com.utsem.consultorioSJLF.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	String cedulaSJLF;
	@Column
	String nombreSJLF;
	@Column
	String apellidosSJLF;
	
	//Seters y getters
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCedulaSJLF() {
		return cedulaSJLF;
	}
	public void setCedulaSJLF(String cedulaSJLF) {
		this.cedulaSJLF = cedulaSJLF;
	}
	public String getNombreSJLF() {
		return nombreSJLF;
	}
	public void setNombreSJLF(String nombreSJLF) {
		this.nombreSJLF = nombreSJLF;
	}
	public String getApellidosSJLF() {
		return apellidosSJLF;
	}
	public void setApellidosSJLF(String apellidosSJLF) {
		this.apellidosSJLF = apellidosSJLF;
	}
	
}
