package com.example.empleados.empleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tareas")
public class TareaControllers {

    @Autowired
    private TareaRepository tareaRepo;

    @Autowired
    private EmpleadoRepository empleadoRepo;

    // Asignamos la tarea a un empleado
    @PostMapping("/asignar")
    public ResponseEntity<Tarea> asignarTarea(@RequestParam Long empleadoId, @RequestBody Tarea tarea) {
        return empleadoRepo.findById(empleadoId)
            .map(empleado -> {
                tarea.setEmpleado(empleado);
                tarea.setFechaAsignacion(LocalDate.now());
                tarea.setEstado("Fecha asignada: ");
                Tarea guardada = tareaRepo.save(tarea);
                return ResponseEntity.ok(guardada);
            })
            .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    // Obtenemos las tareas asignadas de un empleado
    @GetMapping("/empleado/{empleadoId}")
    public List<Tarea> obtenerTareasPorEmpleado(@PathVariable Long empleadoId) {
        return tareaRepo.findByEmpleadoId(empleadoId);
    }
    
    // Borramos o terminamos las tareas del empleado al que asignamos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        if (tareaRepo.existsById(id)) {
            tareaRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
