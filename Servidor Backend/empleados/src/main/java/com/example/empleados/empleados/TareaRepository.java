package com.example.empleados.empleados;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEmpleadoId(Long empleadoId);
    
}