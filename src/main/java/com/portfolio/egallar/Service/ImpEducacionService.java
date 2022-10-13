package com.portfolio.egallar.Service;

import com.portfolio.egallar.Entity.Educacion;
import com.portfolio.egallar.Repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {

    @Autowired
    IEducacionRepository iEducacionRepository;

    public List<Educacion> list() {
        return iEducacionRepository.findAll();
    }

    public Optional<Educacion> getOne(int id) {

        return iEducacionRepository.findById(id);
    }

    public Optional<Educacion> getByNombreEduc(String nombreEduc) {

        return iEducacionRepository.findByNombreEduc(nombreEduc);
    }

    public void save(Educacion educacion) {
        iEducacionRepository.save(educacion);
    }

    public void delete(int id) {
        iEducacionRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return iEducacionRepository.existsById(id);
    }

    public boolean existsByNombreEduc(String nombreEduc) {
        return iEducacionRepository.existsByNombreEduc(nombreEduc);
    }

}
