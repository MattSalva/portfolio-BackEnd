package com.portfolio.mathiassalva.Controller;

import com.portfolio.mathiassalva.Dto.dtoSkills;
import com.portfolio.mathiassalva.Entity.Skills;
import com.portfolio.mathiassalva.Security.Controller.Mensaje;
import com.portfolio.mathiassalva.Service.SSkills;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://frontend-mathiassalva.web.app", "http://localhost:4200"})
@RequestMapping("skills")
public class CSkills {
    @Autowired
    SSkills sSkills;

    @GetMapping("/list")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        Skills skills = sSkills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        sSkills.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sSkills.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Skills ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtohys.getNombre(), dtohys.getPorcentaje());
        sSkills.save(skills);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtohys) {
        //Validamos si existe el ID
        if (!sSkills.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (sSkills.existsByNombre(dtohys.getNombre()) && sSkills.getByNombre(dtohys.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Skills ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = sSkills.getOne(id).get();
        skills.setNombre(dtohys.getNombre());
        skills.setPorcentaje(dtohys.getPorcentaje());

        sSkills.save(skills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }


}
