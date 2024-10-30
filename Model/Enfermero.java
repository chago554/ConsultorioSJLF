package com.utsem.consultorioSJLF.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Enfermero {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String nombreSJLF;
	@Column(unique = true)
	String curpSJLF;
	
	//setters y getters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreSJLF() {
		return nombreSJLF;
	}
	public void setNombreSJLF(String nombreSJLF) {
		this.nombreSJLF = nombreSJLF;
	}
	public String getCurpSJLF() {
		return curpSJLF.toUpperCase();
	}
	public void setCurpSJLF(String curpSJLF) {
		this.curpSJLF = curpSJLF.toUpperCase();
	}
	

	
		
}
