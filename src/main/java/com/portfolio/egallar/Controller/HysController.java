package com.portfolio.egallar.Controller;

import com.portfolio.egallar.Dto.DtoHys;
import com.portfolio.egallar.Entity.Hys;
import com.portfolio.egallar.Security.Controller.Mensaje;
import com.portfolio.egallar.Service.ImpHysService;
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
@RequestMapping("/skill")
@CrossOrigin(origins = {"https://frontendegallar.web.app","http://localhost:4200"})
public class HysController {

    @Autowired
    ImpHysService impHysService;

    @GetMapping("/lista")
    public ResponseEntity<List<Hys>> list() {

        List<Hys> list = impHysService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getByID(@PathVariable("id") int id) {
        if (!impHysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Hys hys = impHysService.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //Valida si existe el id antes de poder borrar la experiencia
        if (!impHysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        impHysService.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoHys dtoHys) {
        if (StringUtils.isBlank(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio ACA"), HttpStatus.BAD_REQUEST);
        }
        if (impHysService.existsByNombre(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("Skill ya existente"), HttpStatus.BAD_REQUEST);
        }

        Hys hys = new Hys(dtoHys.getNombre(), dtoHys.getPorcentaje());
        impHysService.save(hys);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHys dtoHys) {
        //Valida si existe el id antes de poder actualizar la experiencia
        if (!impHysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        //Valida que la experiencia no este repetida comparando nombres
        if (impHysService.existsByNombre(dtoHys.getNombre()) && impHysService.getByNombre(dtoHys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Skill ya existente"), HttpStatus.BAD_REQUEST);
        }
        //Valida que el campo nombre no este vacio
        if (StringUtils.isBlank(dtoHys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        }

        //Valida que el campo porcentaje no este vacio
        if (StringUtils.isBlank(Integer.toString(dtoHys.getPorcentaje()))) {
            return new ResponseEntity(new Mensaje("El porcentaje no puede estar en blanco"), HttpStatus.BAD_REQUEST);
        }
        Hys hys = impHysService.getOne(id).get();
        hys.setNombre(dtoHys.getNombre());
        hys.setPorcentaje(dtoHys.getPorcentaje());

        impHysService.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}
