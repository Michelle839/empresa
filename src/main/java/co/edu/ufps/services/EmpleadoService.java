package co.edu.ufps.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	 
	 
	 
	 public List<EmpleadoDTO> obtenerTodosLosEmpleados2() {
		    return empleadoRepository.findAll().stream()
		            .map(empleado -> new EmpleadoDTO(
		                    empleado.getNombre(),
		                    empleado.getEmail(),
		                    empleado.getProyectos().stream()
		                            .map(proyecto -> new ProyectoDTO(proyecto.getNombre(), proyecto.getDescripcion()))
		                            .collect(Collectors.toList())))
		            .collect(Collectors.toList());
		}

	 
	 public List<Empleado> crearEmpleadosConProyectos1(List<Empleado> empleados) {
	        List<Empleado> empleadosGuardados = new ArrayList<>();

	        // Recorremos la lista de empleados que viene en el JSON
	        for (Empleado empleado : empleados) {
	            // Guardar los proyectos asociados (si la relación está bien mapeada, CascadeType.ALL lo manejará)
	            for (Proyecto proyecto : empleado.getProyectos()) {
	                // Asociar explícitamente el empleado a cada proyecto
	                proyecto.setEmpleado(empleado);
	            }

	            // Guardamos el empleado junto con sus proyectos asociados en cascada
	            Empleado empleadoGuardado = empleadoRepository.save(empleado);
	            empleadosGuardados.add(empleadoGuardado);
	        }

	        return empleadosGuardados;
	    }

	 
//	 // metodo para crear un solo empleado con sus respectivos proyectos
//	 public Empleado crearEmpleadoConProyectos(EmpleadoDTO empleadoDTO) {
//		    Empleado empleado = new Empleado();
//		    empleado.setNombre(empleadoDTO.getNombre());
//		    empleado.setEmail(empleadoDTO.getEmail());
//		    empleado.setTelefono(empleadoDTO.getTelefono());
//
//		    empleado = empleadoRepository.save(empleado);
//
//		    for (ProyectoDTO proyectoDTO : empleadoDTO.getProyectos()) {
//		        Proyecto proyecto = new Proyecto();
//		        proyecto.setNombre(proyectoDTO.getNombre());
//		        proyecto.setDescripcion(proyectoDTO.getDescripcion());
//		        proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
//		        proyecto.setFechaFin(proyectoDTO.getFechaFin());
//		        empleado.addProyecto(proyecto);
//		    }
//
//		    return empleadoRepository.save(empleado);
//		}
//	 
	 
	 
	 
//	 //rcibe la lista de los empeados con las listas de sus proyectos
//	 public List<Empleado> crearEmpleadosConProyectos(List<EmpleadoDTO> empleadosDTO) {
//	        List<Empleado> empleadosGuardados = new ArrayList<>();
//
//	        // Iteramos sobre la lista de empleadosDTO
//	        for (EmpleadoDTO empleadoDTO : empleadosDTO) {
//	            // Crear el objeto Empleado a partir del DTO
//	            Empleado empleado = new Empleado();
//	            empleado.setNombre(empleadoDTO.getNombre());
//	            empleado.setEmail(empleadoDTO.getEmail());
//	            empleado.setTelefono(empleadoDTO.getTelefono());
//
//	            // Guardar el empleado primero para obtener el ID
//	            empleado = empleadoRepository.save(empleado);
//
//	            // Crear los proyectos y asociarlos al empleado
//	            for (ProyectoDTO proyectoDTO : empleadoDTO.getProyectos()) {
//	                Proyecto proyecto = new Proyecto();
//	                proyecto.setNombre(proyectoDTO.getNombre());
//	                proyecto.setDescripcion(proyectoDTO.getDescripcion());
//	                proyecto.setFechaInicio(proyectoDTO.getFechaInicio());
//	                proyecto.setFechaFin(proyectoDTO.getFechaFin());
//
//	                // Asociar el proyecto al empleado
//	                empleado.addProyecto(proyecto);
//	            }
//
//	            // Guardar los proyectos (también guardará el empleado porque la relación está en cascada)
//	            empleado = empleadoRepository.save(empleado);
//	            empleadosGuardados.add(empleado);
//	        }
//
//	        return empleadosGuardados;
//	    }

	 
	
	 public EmpleadoDTO toEmpleadoDTO(Empleado empleado) {
		    EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		    empleadoDTO.setNombre(empleado.getNombre());
		    empleadoDTO.setEmail(empleado.getEmail());

		    // Convertir la lista de proyectos asociados al empleado
		    List<ProyectoDTO> proyectosDTO = empleado.getProyectos().stream()
		            .map(this::toProyectoDTO) // Convertir cada proyecto a ProyectoDTO
		            .toList();

		    empleadoDTO.setProyectos(proyectosDTO);

		    return empleadoDTO;
		}

		public ProyectoDTO toProyectoDTO(Proyecto proyecto) {
		    ProyectoDTO proyectoDTO = new ProyectoDTO();
		    proyectoDTO.setNombre(proyecto.getNombre());
		    proyectoDTO.setDescripcion(proyecto.getDescripcion());
		    return proyectoDTO;
		}
  
	 
	 public List<EmpleadoDTO> obtenerTodosLosEmpleados() {
	        List<Empleado> empleados = empleadoRepository.findAll();
	        return empleados.stream()
	                .map(this::toEmpleadoDTO) // Usa el método de conversión existente
	                .toList();
	    }
	    
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
