package com.empleados.gestor_empleados.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empleados.gestor_empleados.clases.Empleado;
import com.empleados.gestor_empleados.repository.EmpleadoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class EmpleadoController {

	@Autowired

	private EmpleadoRepository empleadoRepository;

	@GetMapping("/empleados")
	public List<Empleado> listarTodosLosEmpleados() {
		return empleadoRepository.findAll();
	}

	@GetMapping("/empleados/{id}")
	private ResponseEntity<Empleado> findById(@PathVariable Long id) {
		Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
		if (empleadoOptional.isPresent()) {
			return ResponseEntity.ok(empleadoOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/empleados")
	public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
		if (empleado.getNombre() == null || empleado.getNombre().isEmpty() || empleado.getApellido() == null
				|| empleado.getApellido().isEmpty() || empleado.getEmail() == null || empleado.getEmail().isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}

		Empleado nuevoEmpleado = empleadoRepository.save(empleado);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
	}

	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
		Optional<Empleado> empleadoOptional = empleadoRepository.findById(id);
		if (empleadoOptional.isPresent()) {
			Empleado empleadoExistente = empleadoOptional.get();
			empleadoExistente.setNombre(empleadoDetalles.getNombre());
			empleadoExistente.setApellido(empleadoDetalles.getApellido());
			empleadoExistente.setEmail(empleadoDetalles.getEmail());

			Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
			return ResponseEntity.ok(empleadoActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Long id) {
		if (empleadoRepository.existsById(id)) {
			empleadoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
