package com.portfolio.mathiassalva.Controller;
import com.portfolio.mathiassalva.Dto.dtoPersona;
import com.portfolio.mathiassalva.Entity.Persona;
import com.portfolio.mathiassalva.Security.Controller.Mensaje;
import com.portfolio.mathiassalva.Service.SPersona;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://frontend-mathiassalva.web.app", "http://localhost:4200"})
public class CPersona {
    @Autowired
    SPersona sPersona;

    @GetMapping("/list")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = sPersona.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.BAD_REQUEST);
        Persona persona = sPersona.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
        if(!sPersona.existsById(id)){
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        if(sPersona.existsByNombre(dtopersona.getNombre()) && sPersona.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Campo nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(dtopersona.getDescripcion())){
            return new ResponseEntity(new Mensaje("Campo descripcion no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = sPersona.getOne(id).get();

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());

        sPersona.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada con exito"), HttpStatus.OK);
    }



    /*

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sPersona.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        sPersona.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getEduNombre())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(dtopersona.getEduDesc())){
            return new ResponseEntity(new Mensaje("Descripcion obligatoria"), HttpStatus.BAD_REQUEST);
        }

        if (sPersona.existsByEduNombre(dtopersona.getEduNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = new Persona(dtopersona.getEduNombre(), dtopersona.getEduDesc());

        sPersona.save(persona);

        return new ResponseEntity<>(new Mensaje("Persona creada"), HttpStatus.OK);
    }
    */
}
