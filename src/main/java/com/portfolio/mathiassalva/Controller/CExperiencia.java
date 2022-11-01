package com.portfolio.mathiassalva.Controller;

import com.portfolio.mathiassalva.Dto.dtoExperiencia;
import com.portfolio.mathiassalva.Entity.Experiencia;
import com.portfolio.mathiassalva.Security.Controller.Mensaje;
import com.portfolio.mathiassalva.Service.Sxp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("experience")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperiencia {
    @Autowired
    Sxp sxp;

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sxp.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sxp.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sxp.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoxp){
        if(StringUtils.isBlank(dtoxp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sxp.existsByNombreE(dtoxp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = new Experiencia(dtoxp.getNombreE(), dtoxp.getDescripcionE());
        sxp.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoxp){
        if(!sxp.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        if(sxp.existsByNombreE(dtoxp.getNombreE()) && sxp.getByNombreE(dtoxp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoxp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Experiencia experiencia = sxp.getOne(id).get();
        experiencia.setNombreE(dtoxp.getNombreE());
        experiencia.setDescripcionE(dtoxp.getDescripcionE());

        sxp.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia Actualizada"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sxp.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        sxp.delete(id);

        return new ResponseEntity(new Mensaje("La experiencia fue eliminada con exito"), HttpStatus.OK);

    }

}
