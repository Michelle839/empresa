package co.edu.ufps.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmpleadoDTO {

	private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private List<ProyectoDTO> proyectos;
}
