package com.utsem.consultorioSJLF.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utsem.consultorioSJLF.enums.EstatusCita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Cita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	LocalDateTime fechaSJLF;
	
	@Column
	EstatusCita estatus = EstatusCita.Programada;
	
	//para relacionar las entidades --> muchos a uno
	@ManyToOne
	Paciente paciente;
	
	@ManyToOne
	Medico medico;
			
	String nuevaFechaSJLF ="";

	//String nuevoMinuto="";
	
	//se retorna un array con los todos los estatus de las citas
	public EstatusCita [] getEstatusCita(){
		return EstatusCita.values();
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime getFechaHTML() {
		return fechaSJLF;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getFechaSJLF() {
		return fechaSJLF;
	}
	public void setFechaSJLF(LocalDateTime fechaSJLF) {
		this.fechaSJLF = fechaSJLF;
	}
	public EstatusCita getEstatus() {
		return estatus;
	}
	public void setEstatus(EstatusCita estatus) {
		this.estatus = estatus;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public String getNuevaFechaSJLF() {
		return nuevaFechaSJLF;
	}

	public void setNuevaFechaSJLF(String nuevaFechaSJLF) {
		this.nuevaFechaSJLF = nuevaFechaSJLF;
	}

	
}
