package com.example.empleados.empleados;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	List<Empleado> findByNombreContainingIgnoreCase(String nombre);

	List<Empleado> findByApellidoContainingIgnoreCase(String apellido);

	List<Empleado> findByPuestoContainingIgnoreCase(String puesto);

	List<Empleado> findByCodigoEmpleadoContainingIgnoreCase(String codigoEmpleado);
}
