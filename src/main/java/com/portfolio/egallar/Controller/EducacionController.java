package com.portfolio.egallar.Controller;

import com.portfolio.egallar.Dto.DtoEducacion;
import com.portfolio.egallar.Entity.Educacion;
import com.portfolio.egallar.Security.Controller.Mensaje;
import com.portfolio.egallar.Service.ImpEducacionService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin (origins = {"http://localhost:4200", "https://frontendegallar.web.app"})
public class EducacionController {
    
    @Autowired
    ImpEducacionService impEducacionService;
    
    @GetMapping("/lista")
    public ResponseEntity<List <Educacion>> list (){
        List<Educacion> list = impEducacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!impEducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = impEducacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!impEducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        impEducacionService.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEducacion){
        if(StringUtils.isBlank(dtoEducacion.getNombreEduc())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(impEducacionService.existsByNombreEduc(dtoEducacion.getNombreEduc())){
            return new ResponseEntity(new Mensaje("El nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(dtoEducacion.getNombreEduc(), dtoEducacion.getDescripcionEduc());
        impEducacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEducacion){
        
        if(!impEducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(impEducacionService.existsByNombreEduc(dtoEducacion.getNombreEduc()) && impEducacionService.getByNombreEduc(dtoEducacion.getNombreEduc()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoEducacion.getNombreEduc())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = impEducacionService.getOne(id).get();
        
        educacion.setNombreEduc(dtoEducacion.getNombreEduc());
        educacion.setDescripcionEduc(dtoEducacion.getDescripcionEduc());
        
        impEducacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);
    }
}
