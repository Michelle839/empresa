package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.dto.EmpleadoDTO;
import co.edu.ufps.entities.Empleado;
import co.edu.ufps.services.EmpleadoService;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
	@Autowired
	private EmpleadoService empleadoService;

//	@PostMapping("/crear")
//	    public ResponseEntity<Empleado> crearEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
//	        Empleado empleado = empleadoService.crearEmpleadoConProyectos(empleadoDTO);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(empleado);
//	}
//	
//	@PostMapping("/crear-multiples")
//	public ResponseEntity<Void> crearEmpleados(@RequestBody List<EmpleadoDTO> empleadosDTO) {
//	    // Llama al servicio para guardar los empleados y sus proyectos
//	    empleadoService.crearEmpleadosConProyectos(empleadosDTO);
//
//	    // Devuelve un estado HTTP 201 (Created) sin contenido en el cuerpo
//	    return ResponseEntity.status(HttpStatus.CREATED).build();
//	}
	
	 @GetMapping("/todos/empleados")
	    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados2() {
	        // Llama al servicio para obtener todos los empleados y convertirlos en DTOs
	        List<EmpleadoDTO> empleadosDTO = empleadoService.obtenerTodosLosEmpleados2();

	        // Retorna la lista de empleados en formato JSON (response 200 OK)
	        return ResponseEntity.ok(empleadosDTO);
	    }
	 
	@PostMapping("/crear-multiples")
    public List<Empleado> crearEmpleadosConProyectos(@RequestBody List<Empleado> empleados) {
        return empleadoService.crearEmpleadosConProyectos1(empleados);
    }

	@PostMapping
	public Empleado create(@RequestBody Empleado Empleado) {
		return empleadoService.create(Empleado);
	}
	
	@GetMapping
	public List<Empleado>  list() {
		return empleadoService.list();
	}
	
	@GetMapping("/todos")
    public ResponseEntity<List<EmpleadoDTO>> obtenerTodosLosEmpleados() {
        List<EmpleadoDTO> empleadosDTO = empleadoService.obtenerTodosLosEmpleados();
        return ResponseEntity.ok(empleadosDTO);
    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> getById(@PathVariable Integer id) {
		Optional<Empleado> Empleado = empleadoService.getById(id);
		return Empleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado EmpleadoDetails) {
		Optional<Empleado> updatedEmpleado = empleadoService.update(id, EmpleadoDetails);
		return updatedEmpleado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = empleadoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	

}
