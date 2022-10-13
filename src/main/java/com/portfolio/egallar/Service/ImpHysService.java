package com.portfolio.egallar.Service;

import com.portfolio.egallar.Entity.Hys;
import com.portfolio.egallar.Repository.IHysRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpHysService {
    
    @Autowired
    IHysRepository iHysRepository;
    
    public List<Hys> list(){
        return iHysRepository.findAll();
    }
    
    public Optional<Hys> getOne(int id){
        return iHysRepository.findById(id);
    }
    
    public Optional<Hys> getByNombre(String nombre){
        return iHysRepository.findByNombre(nombre);
    }
    
    public void save (Hys skill){
        iHysRepository.save(skill);
    }
    
    public void delete (int id){
        iHysRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return iHysRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return iHysRepository.existsByNombre(nombre);
    }
}
