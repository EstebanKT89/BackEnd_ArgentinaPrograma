package com.portfolio.egallar.Controller;

import com.portfolio.egallar.Dto.DtoPersona;
import com.portfolio.egallar.Entity.Persona;
import com.portfolio.egallar.Security.Controller.Mensaje;
import com.portfolio.egallar.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://frontendegallar.web.app"})
public class PersonaController {

    @Autowired
    ImpPersonaService impPersonaService;
    
    @GetMapping("/lista")
    public ResponseEntity<List <Persona>> list (){
        List<Persona> list = impPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!impPersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id")int id){
//        if(!impPersonaService.existsById(id)){
//            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
//        }
//        impPersonaService.delete(id);
//        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
//    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(impPersonaService.existsByNombre(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(dtoPersona.getNombre(),dtoPersona.getApellido(), dtoPersona.getDescripcion(), dtoPersona.getImg());
        impPersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Educación creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtoPersona){
        
        if(!impPersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(impPersonaService.existsByNombre(dtoPersona.getNombre()) && impPersonaService.getByNombre(dtoPersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"),HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = impPersonaService.getOne(id).get();
        
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setDescripcion(dtoPersona.getDescripcion());
        persona.setImg(dtoPersona.getImg());
        
        impPersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
}
