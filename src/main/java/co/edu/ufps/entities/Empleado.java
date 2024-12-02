
package co.edu.ufps.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="empleado")
@Data
public class Empleado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
		
	@Column(name="nombre", length=100)
	private String nombre;
	
	@Column(name="email", length=100)
	private String email;
	
	@Column(name="telefono", length=20)
	private String telefono;
	
	@OneToMany(mappedBy = "empleado", cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Proyecto> proyectos = new ArrayList<>();  // Inicializamos la lista para evitar NullPointerException
	
	
	public void addProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
        proyecto.setEmpleado(this);
    }

    public void removeProyecto(Proyecto proyecto) {
        proyectos.remove(proyecto);
        proyecto.setEmpleado(null);
    }
	
}
