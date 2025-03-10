package com.example.empleados.empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

	// Buscar empleados por nombre
	@GetMapping("/buscar/nombre")
	public List<Empleado> buscarPorNombre(@RequestParam String nombre) {
		return empleadoRepository.findByNombreContainingIgnoreCase(nombre);
	}

	// Buscar empleados por apellido
	@GetMapping("/buscar/apellido")
	public List<Empleado> buscarPorApellido(@RequestParam String apellido) {
		return empleadoRepository.findByApellidoContainingIgnoreCase(apellido);
	}

	// Buscar empleados por puesto
	@GetMapping("/buscar/puesto")
	public List<Empleado> buscarPorPuesto(@RequestParam String puesto) {
		return empleadoRepository.findByPuestoContainingIgnoreCase(puesto);
	}

	// Buscar empleados por código de empleado
	@GetMapping("/buscar/codigo")
	public List<Empleado> buscarPorCodigoEmpleado(@RequestParam String codigo) {
		return empleadoRepository.findByCodigoEmpleadoContainingIgnoreCase(codigo);
	}

	// Crear un nuevo empleado
	@PostMapping
	public Empleado createEmpleado(@RequestBody Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	// Actualizar un empleado
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleadoDetalles) {
		return empleadoRepository.findById(id).map(empleado -> {
			empleado.setNombre(empleadoDetalles.getNombre());
			empleado.setApellido(empleadoDetalles.getApellido());
			empleado.setPuesto(empleadoDetalles.getPuesto());
			empleado.setCodigoEmpleado(empleadoDetalles.getCodigoEmpleado());
			return ResponseEntity.ok(empleadoRepository.save(empleado));
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Eliminar un empleado
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
		if (empleadoRepository.existsById(id)) {
			empleadoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
