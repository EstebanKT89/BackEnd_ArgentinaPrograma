package com.portfolio.egallar.Controller;

import com.portfolio.egallar.Dto.DtoProyectos;
import com.portfolio.egallar.Entity.Proyectos;
import com.portfolio.egallar.Security.Controller.Mensaje;
import com.portfolio.egallar.Service.ImpProyectosService;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = {"https://frontendegallar.web.app","http://localhost:4200"})
public class ProyectosController {

    @Autowired
    ImpProyectosService impProyectosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = impProyectosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {

        if ((!impProyectosService.existsById(id))) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        Proyectos proyecto = impProyectosService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!impProyectosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        impProyectosService.delete(id);
        return new ResponseEntity(new Mensaje("Proeycto eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoProyectos) {

        if(StringUtils.isBlank(dtoProyectos.getNombreProyec())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(impProyectosService.existsByNombreProyec(dtoProyectos.getNombreProyec())){
            return new ResponseEntity(new Mensaje("El nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyecto = new Proyectos(dtoProyectos.getNombreProyec(),dtoProyectos.getFecha(), dtoProyectos.getDescripcionProyec(), dtoProyectos.getLinkProyec(), dtoProyectos.getImgCap());
        impProyectosService.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody DtoProyectos dtoProyecto){
        if(!impProyectosService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(impProyectosService.existsByNombreProyec(dtoProyecto.getNombreProyec()) && impProyectosService.getByNombreProyec(dtoProyecto.getNombreProyec()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProyecto.getNombreProyec())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyecto = impProyectosService.getOne(id).get();
        
        proyecto.setNombreProyec(dtoProyecto.getNombreProyec());
        proyecto.setFecha(dtoProyecto.getFecha());
        proyecto.setDescripcionProyec(dtoProyecto.getDescripcionProyec());
        proyecto.setLinkProyec(dtoProyecto.getLinkProyec());
        proyecto.setImgCap(dtoProyecto.getImgCap());
        
        impProyectosService.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}
