package com.example.empleados.empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/empleados")
public class EmpleadoControllers {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	// Obtener todos los empleados
	@GetMapping
	public List<Empleado> getAllEmpleados() {
		return empleadoRepository.findAll();
	}

	// Obtenemos un empleado por su ID
	@GetMapping("/{id}")
	public Optional<Empleado> obtenerSurtidorPorId(@PathVariable Long id) {
		return empleadoRepository.findById(id);
	}

	// Creamos un empleado con los datos necesarios
	// El signo de interrogación sirve para poder usar cualquier tipo de datos
	@PostMapping
	public ResponseEntity<?> createEmpleado(@RequestBody Empleado empleado) {
		try {
			Empleado nuevo = empleadoRepository.save(empleado);
			return ResponseEntity.ok(nuevo);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("El código de empleado ya existe.");
		}
	}

	// Actualizamos a un empleado de la lista
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
		return empleadoRepository.findById(id).map(empleado -> {
			empleado.setNombre(empleadoDetalles.getNombre());
			empleado.setApellido(empleadoDetalles.getApellido());
			empleado.setPuesto(empleadoDetalles.getPuesto());
			empleado.setCodigoEmpleado(empleadoDetalles.getCodigoEmpleado());
			try {
				return ResponseEntity.ok(empleadoRepository.save(empleado));
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("El código de empleado ya está en uso.");
			}
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Eliminamos un empleado de la lista
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
		if (empleadoRepository.existsById(id)) {
			empleadoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
