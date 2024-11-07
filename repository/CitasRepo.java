package com.utsem.consultorioSJLF.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.utsem.consultorioSJLF.Model.Cita;

public interface CitasRepo extends JpaRepository<Cita, Long> {
	List<Cita> findByOrderByFechaSJLFAsc();
	
}
