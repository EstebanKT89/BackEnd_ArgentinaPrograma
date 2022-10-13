package com.portfolio.egallar.Repository;

import com.portfolio.egallar.Entity.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectosRepository extends JpaRepository<Proyectos, Integer> {

    public Optional<Proyectos> findByNombreProyec(String nombreProyec);

    public boolean existsByNombreProyec(String nombreProyec);
    
}
