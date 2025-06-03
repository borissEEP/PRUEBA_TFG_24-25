package com.example.empleados.empleados;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tarea")
public class Tarea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descripcion;
	private LocalDate fechaAsignacion;
	private String estado;

	@ManyToOne
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;

	public Tarea() {
	}

	public Tarea(String descripcion, LocalDate fechaAsignacion, String estado, Empleado empleado) {
		this.descripcion = descripcion;
		this.fechaAsignacion = fechaAsignacion;
		this.estado = estado;
		this.empleado = empleado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(LocalDate fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
}
