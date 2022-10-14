package com.portfolio.egallar.Controller;

import com.portfolio.egallar.Dto.DtoExperiencia;
import com.portfolio.egallar.Entity.Experiencia;
import com.portfolio.egallar.Security.Controller.Mensaje;
import com.portfolio.egallar.Service.ImpExperienciaService;
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
@RequestMapping("/expLab")
@CrossOrigin(origins = {"http://localhost:4200", "https://frontendegallar.web.app"})
public class ExperienciaController {

    @Autowired
    ImpExperienciaService impExperienciaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {

        List<Experiencia> list = impExperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getByID(@PathVariable("id") int id) {
        if (!impExperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = impExperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Valida si existe el id antes de poder borrar la experiencia
        if (!impExperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        impExperienciaService.delete(id);

        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExp) {
        if (StringUtils.isBlank(dtoExp.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (impExperienciaService.existsByNombreExp(dtoExp.getNombreExp())) {
            return new ResponseEntity(new Mensaje("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = new Experiencia(dtoExp.getNombreExp(), dtoExp.getDescripcionExp());
        impExperienciaService.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoExp) {
        //Valida si existe el id antes de poder actualizar la experiencia
        if (!impExperienciaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        //Valida que la experiencia no este repetida comparando nombres
        if (impExperienciaService.existsByNombreExp(dtoExp.getNombreExp()) && impExperienciaService.getByNombreExp(dtoExp.getNombreExp()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        }
        //Valida que el campo nombre no este vacio
        if (StringUtils.isBlank(dtoExp.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = impExperienciaService.getOne(id).get();
        experiencia.setNombreExp(dtoExp.getNombreExp());
        experiencia.setDescripcionExp(dtoExp.getDescripcionExp());

        impExperienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);

    }

    
}
