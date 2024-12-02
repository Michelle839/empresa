package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Proyecto;
import co.edu.ufps.services.ProyectoService;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
	@Autowired
	private ProyectoService proyectoService;

	@GetMapping
	public List<Proyecto>  list() {
		
		return proyectoService.list();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Proyecto> getById(@PathVariable Integer id) {
		Optional<Proyecto> Proyecto = proyectoService.getById(id);
		return Proyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Proyecto create(@RequestBody Proyecto Proyecto) {
		return proyectoService.create(Proyecto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proyecto> update(@PathVariable Integer id, @RequestBody Proyecto ProyectoDetails) {
		Optional<Proyecto> updatedProyecto = proyectoService.update(id, ProyectoDetails);
		return updatedProyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = proyectoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	

}
