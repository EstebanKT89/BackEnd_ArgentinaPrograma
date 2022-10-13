package com.portfolio.egallar.Service;

import com.portfolio.egallar.Entity.Proyectos;
import com.portfolio.egallar.Repository.IProyectosRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpProyectosService {

    @Autowired
    IProyectosRepository iProyectosRepository;

    public List<Proyectos> list() {
        return iProyectosRepository.findAll();
    }

    public Optional<Proyectos> getOne(int id) {
        return iProyectosRepository.findById(id);
    }

    public Optional<Proyectos> getByNombreProyec(String nombreProyec) {
        
        return iProyectosRepository.findByNombreProyec(nombreProyec);
    }

    public void save(Proyectos proyecto) {
        iProyectosRepository.save(proyecto);
    }

    public void delete(int id) {
        iProyectosRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iProyectosRepository.existsById(id);
    }

    public boolean existsByNombreProyec(String nombreProyec) {
        return iProyectosRepository.existsByNombreProyec(nombreProyec);
    }
}
