package com.utsem.consultorioSJLF.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utsem.consultorioSJLF.enums.EstatusConsulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	Paciente paciente;
	
	@ManyToOne
	Medico medico;
	
	@ManyToOne
	Enfermero enfermero;
	@Column
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	@CreationTimestamp
	LocalDateTime fechaEsperaSJLF;
	
	@Column
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	@CreationTimestamp
	LocalDateTime fechaInicioSJLF;
	
	@Column
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	@CreationTimestamp
	LocalDateTime fechaTerminoSJLF;
	
	//toma de signos
	@Column
	Float pesoSJLF;
	@Column
	Float alturaSJLF;
	@Column
	Integer sistoleSJLF;
	@Column
	Integer diastoleSJLF;
	@Column
	Float temperaturaSJLF;
	@Column
	Integer oxigenoSJLF;
	@Column
	Integer fcSJLF;
	@Column
	String sintomasSJLF;
	@Column
	String observacionesSJLF;
	@Column
	EstatusConsulta estatusConsulta = EstatusConsulta.Esperando;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Enfermero getEnfermero() {
		return enfermero;
	}
	public void setEnfermero(Enfermero enfermero) {
		this.enfermero = enfermero;
	}
	public LocalDateTime getFechaEsperaSJLF() {
		return fechaEsperaSJLF;
	}
	public void setFechaEsperaSJLF(LocalDateTime fechaEsperaSJLF) {
		this.fechaEsperaSJLF = fechaEsperaSJLF;
	}
	public LocalDateTime getFechaInicioSJLF() {
		return fechaInicioSJLF;
	}
	public void setFechaInicioSJLF(LocalDateTime fechaInicioSJLF) {
		this.fechaInicioSJLF = fechaInicioSJLF;
	}
	public LocalDateTime getFechaTerminoSJLF() {
		return fechaTerminoSJLF;
	}
	public void setFechaTerminoSJLF(LocalDateTime fechaTerminoSJLF) {
		this.fechaTerminoSJLF = fechaTerminoSJLF;
	}
	public Float getPesoSJLF() {
		return pesoSJLF;
	}
	public void setPesoSJLF(Float pesoSJLF) {
		this.pesoSJLF = pesoSJLF;
	}
	public Float getAlturaSJLF() {
		return alturaSJLF;
	}
	public void setAlturaSJLF(Float alturaSJLF) {
		this.alturaSJLF = alturaSJLF;
	}
	public Integer getSistoleSJLF() {
		return sistoleSJLF;
	}
	public void setSistoleSJLF(Integer sistoleSJLF) {
		this.sistoleSJLF = sistoleSJLF;
	}
	public Integer getDiastoleSJLF() {
		return diastoleSJLF;
	}
	public void setDiastoleSJLF(Integer diastoleSJLF) {
		this.diastoleSJLF = diastoleSJLF;
	}
	public Float getTemperaturaSJLF() {
		return temperaturaSJLF;
	}
	public void setTemperaturaSJLF(Float temperaturaSJLF) {
		this.temperaturaSJLF = temperaturaSJLF;
	}
	public Integer getOxigenoSJLF() {
		return oxigenoSJLF;
	}
	public void setOxigenoSJLF(Integer oxigenoSJLF) {
		this.oxigenoSJLF = oxigenoSJLF;
	}
	public Integer getFcSJLF() {
		return fcSJLF;
	}
	public void setFcSJLF(Integer fcSJLF) {
		this.fcSJLF = fcSJLF;
	}
	public String getSintomasSJLF() {
		return sintomasSJLF;
	}
	public void setSintomasSJLF(String sintomasSJLF) {
		this.sintomasSJLF = sintomasSJLF;
	}
	public String getObservacionesSJLF() {
		return observacionesSJLF;
	}
	public void setObservacionesSJLF(String observacionesSJLF) {
		this.observacionesSJLF = observacionesSJLF;
	}
	public EstatusConsulta getEstatusConsulta() {
		return estatusConsulta;
	}
	public void setEstatusConsulta(EstatusConsulta estatusConsulta) {
		this.estatusConsulta = estatusConsulta;
	}

	
	
	
	
	
	
	
}
