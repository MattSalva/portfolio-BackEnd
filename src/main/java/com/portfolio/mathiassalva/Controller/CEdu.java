package com.portfolio.mathiassalva.Controller;

import com.portfolio.mathiassalva.Dto.dtoEducacion;
import com.portfolio.mathiassalva.Entity.Educacion;
import com.portfolio.mathiassalva.Security.Controller.Mensaje;
import com.portfolio.mathiassalva.Service.SEdu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu")
@CrossOrigin(origins = "https://frontend-mathiassalva.web.app")
//@CrossOrigin(origins = "http://localhost:4200")
public class CEdu {
    @Autowired
    SEdu sEdu;

    @GetMapping("/list")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEdu.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEdu.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        sEdu.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEdu){
        if(StringUtils.isBlank(dtoEdu.getEduNombre())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(dtoEdu.getEduDesc())){
            return new ResponseEntity(new Mensaje("Descripcion obligatoria"), HttpStatus.BAD_REQUEST);
        }

        if (sEdu.existsByEduNombre(dtoEdu.getEduNombre())){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoEdu.getEduNombre(), dtoEdu.getEduDesc());

        sEdu.save(educacion);

        return new ResponseEntity<>(new Mensaje("Educacion creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoEdu){
        if(!sEdu.existsById(id)){
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        if(sEdu.existsByEduNombre(dtoEdu.getEduNombre()) && sEdu.getByEduNombre(dtoEdu.getEduNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoEdu.getEduNombre())){
            return new ResponseEntity(new Mensaje("Campo nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(dtoEdu.getEduDesc())){
            return new ResponseEntity(new Mensaje("Campo descripcion no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = sEdu.getOne(id).get();

        educacion.setEduNombre(dtoEdu.getEduNombre());
        educacion.setEduDesc(dtoEdu.getEduDesc());

        sEdu.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada con exito"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!sEdu.existsById(id))
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.BAD_REQUEST);
        Educacion educacion = sEdu.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }




}
