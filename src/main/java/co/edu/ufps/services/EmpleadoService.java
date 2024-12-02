package co.edu.ufps.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.dto.EmpleadoDTO;
import co.edu.ufps.dto.ProyectoDTO;
import co.edu.ufps.entities.Empleado;
import co.edu.ufps.entities.Proyecto;
import co.edu.ufps.repositories.EmpleadoRepository;
import co.edu.ufps.repositories.ProyectoRepository;

@Service
public class EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	ProyectoRepository proyectoRepository;
	
	 @Autowired
	 ProyectoService proyectoService; 

	 
	 // metodo para crear un solo empleado con sus respectivos proyectos
	 public Empleado crearEmpleadoConProyectos(EmpleadoDTO empleadoDTO) {
		    Empleado empleado = new Empleado();
		    empleado.setNombre(empleadoDTO.getNombre());
		    empleado.setEmail(empleadoDTO.getEmail());
		    empleado.setTelefono(empleadoDTO.getTelefono());

		    empleado = empleadoRepository.save(empleado);

		    for (ProyectoDTO proyectoDTO : empleadoDTO.getProyectos()) {
		        Proyecto proyecto = new Proyecto();
		        proyecto.setNombre(proyectoDTO.getNombre());
		        proyecto.setDescripcion(proyectoDTO.getDescripcion());
		        proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
		        proyecto.setFechaFin(proyectoDTO.getFechaFin());
		        empleado.addProyecto(proyecto);
		    }

		    return empleadoRepository.save(empleado);
		}
	 
	 
	 
	 
	 //rcibe la lista de los empeados con las listas de sus proyectos
	 public List<Empleado> crearEmpleadosConProyectos(List<EmpleadoDTO> empleadosDTO) {
	        List<Empleado> empleadosGuardados = new ArrayList<>();

	        // Iteramos sobre la lista de empleadosDTO
	        for (EmpleadoDTO empleadoDTO : empleadosDTO) {
	            // Crear el objeto Empleado a partir del DTO
	            Empleado empleado = new Empleado();
	            empleado.setNombre(empleadoDTO.getNombre());
	            empleado.setEmail(empleadoDTO.getEmail());
	            empleado.setTelefono(empleadoDTO.getTelefono());

	            // Guardar el empleado primero para obtener el ID
	            empleado = empleadoRepository.save(empleado);

	            // Crear los proyectos y asociarlos al empleado
	            for (ProyectoDTO proyectoDTO : empleadoDTO.getProyectos()) {
	                Proyecto proyecto = new Proyecto();
	                proyecto.setNombre(proyectoDTO.getNombre());
	                proyecto.setDescripcion(proyectoDTO.getDescripcion());
	                proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
	                proyecto.setFechaFin(proyectoDTO.getFechaFin());

	                // Asociar el proyecto al empleado
	                empleado.addProyecto(proyecto);
	            }

	            // Guardar los proyectos (también guardará el empleado porque la relación está en cascada)
	            empleado = empleadoRepository.save(empleado);
	            empleadosGuardados.add(empleado);
	        }

	        return empleadosGuardados;
	    }

	 
	 public List<Empleado> crearempleadosconproyectos(List<Empleado> empleados) {
	        List<Empleado> empleadosGuardados = new ArrayList<>();

	        for (Empleado empleado : empleados) {
	            // Primero, guarda el empleado para obtener el ID (si no es un nuevo empleado)
	   

	            // Ahora, asocia los proyectos con el empleado
	            for (Proyecto proyecto : empleado.getProyectos()) {
	        
	                empleado.addProyecto(proyecto);
	                
	                // Guarda el proyecto (esto guardará el empleado_id automáticamente debido a la relación en Proyecto)
	                // Si la relación está correctamente configurada con cascada, no es necesario guardar el proyecto explícitamente
	                proyectoRepository.save(proyecto);
	            }

	            empleadosGuardados.add(empleado);
	            empleado = empleadoRepository.save(empleado);
	        }

	        return empleadosGuardados;
	    }

//	    // Método para convertir de entidad a DTO
//	    public EmpleadoDTO toEmpleadoDTO(Empleado empleado) {
//	        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
//	        empleadoDTO.setNombre(empleado.getNombre());
//	        empleadoDTO.setEmail(empleado.getEmail());
//	        empleadoDTO.setTelefono(empleado.getTelefono());
//
//	        // Convertimos proyectos de entidad a DTO
//	        List<ProyectoDTO> proyectosDTO = new ArrayList<>();
//	        for (Proyecto proyecto : empleado.getProyectos()) {
//	            proyectosDTO.add(proyectoService.toProyectoDTO(proyecto));  // Usamos ProyectoService para la conversión
//	        }
//	        empleadoDTO.setProyectos(proyectosDTO);
//
//	        return empleadoDTO;
//	    }
	    
	public List<Empleado> list() {
		return empleadoRepository.findAll();
	}
	
	public Empleado create(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	// Obtener un empleado por ID
	public Optional<Empleado> getById(Integer id) {
		return empleadoRepository.findById(id);
	}

	// Actualizar un empleado existente
	public Optional<Empleado> update(Integer id, Empleado empleadoDetails) {
		Optional<Empleado> optionalempleado = empleadoRepository.findById(id);
		if (!optionalempleado.isPresent()) {
			return Optional.empty();
		}

		Empleado empleado = optionalempleado.get();

		// Actualiza otros campos según sea necesario
		empleado.setNombre(empleadoDetails.getNombre());

		return Optional.of(empleadoRepository.save(empleado));
	}

	// Eliminar un empleado por ID
	public boolean delete(Integer id) {
		if (!empleadoRepository.existsById(id)) {
			return false;
		}
		empleadoRepository.deleteById(id);
		return true;
	}
}
