package com.utsem.consultorioSJLF.Model;

import com.utsem.consultorioSJLF.enums.TipoSangre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(length = 18)
	String curpSJLF;

	@Column
	String nombreSJLF;
	
	@Column
	TipoSangre tipoSangre;
	
	//setters y getters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCurpSJLF() {
		return curpSJLF;
	}
	public void setCurpSJLF(String curpSJLF) {
		this.curpSJLF = curpSJLF;
	}
	public String getNombreSJLF() {
		return nombreSJLF;
	}
	public void setNombreSJLF(String nombreSJLF) {
		this.nombreSJLF = nombreSJLF;
	}
	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}
	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

}
