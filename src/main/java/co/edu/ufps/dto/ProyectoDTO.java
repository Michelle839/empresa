package co.edu.ufps.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ProyectoDTO {

	private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
