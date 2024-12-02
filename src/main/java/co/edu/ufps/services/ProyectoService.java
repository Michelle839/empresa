package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.dto.ProyectoDTO;
import co.edu.ufps.entities.Proyecto;
import co.edu.ufps.repositories.ProyectoRepository;

@Service
public class ProyectoService {

	@Autowired
	ProyectoRepository proyectoRepository;
	
//	
//	
//	// Método para convertir de DTO a entidad pero sin asociar con empleado
//    public Proyecto toProyectoEntity(ProyectoDTO proyectoDTO) {
//        Proyecto proyecto = new Proyecto();
//        proyecto.setNombre(proyectoDTO.getNombre());
//        proyecto.setDescripcion(proyectoDTO.getDescripcion());
//        proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
//        proyecto.setFechaFin(proyectoDTO.getFechaFin());
//        return proyecto;
//    }
//
//    // Método para convertir de entidad a DTO
//    public ProyectoDTO toProyectoDTO(Proyecto proyecto) {
//        ProyectoDTO proyectoDTO = new ProyectoDTO();
//        proyectoDTO.setNombre(proyecto.getNombre());
//        proyectoDTO.setDescripcion(proyecto.getDescripcion());
//        proyectoDTO.setFechaInicio(proyecto.getFechaInicio());
//        proyectoDTO.setFechaFin(proyecto.getFechaFin());
//        return proyectoDTO;
//    }
//	
	
	public List<Proyecto> list() {
		return proyectoRepository.findAll();
	}
	
	public Proyecto create(Proyecto proyecto) {
		return proyectoRepository.save(proyecto);
	}

	// Obtener un proyecto por ID
	public Optional<Proyecto> getById(Integer id) {
		return proyectoRepository.findById(id);
	}

	// Actualizar un proyecto existente
	public Optional<Proyecto> update(Integer id, Proyecto proyectoDetails) {
		Optional<Proyecto> optionalproyecto = proyectoRepository.findById(id);
		if (!optionalproyecto.isPresent()) {
			return Optional.empty();
		}

		Proyecto proyecto = optionalproyecto.get();

		// Actualiza otros campos según sea necesario
		proyecto.setNombre(proyectoDetails.getNombre());

		return Optional.of(proyectoRepository.save(proyecto));
	}

	// Eliminar un proyecto por ID
	public boolean delete(Integer id) {
		if (!proyectoRepository.existsById(id)) {
			return false;
		}
		proyectoRepository.deleteById(id);
		return true;
	}
}
