package co.edu.ufps.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
public class EmpleadoDTO {

    private String nombre;
    private String email;
    private List<ProyectoDTO> proyectos;
}
