package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.ufps.entities.Proyecto;


@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Integer>{
}


