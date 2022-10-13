package com.portfolio.egallar.Repository;

import com.portfolio.egallar.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Integer> {

    public Optional<Educacion> findByNombreEduc(String nombreEduc);

    public boolean existsByNombreEduc(String nombreEduc);
}
