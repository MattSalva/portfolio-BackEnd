package com.portfolio.mathiassalva.Service;

import com.portfolio.mathiassalva.Entity.Educacion;
import com.portfolio.mathiassalva.Repository.REducacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SEdu {
    @Autowired
    REducacion rEducacion;

    public List<Educacion> list() {
        return rEducacion.findAll();
    }

    public Optional<Educacion> getOne(int id){
        return rEducacion.findById(id);
    }

    public Optional<Educacion> getByEduNombre(String eduNombre){
        return rEducacion.findByEduNombre(eduNombre);
    }

    public void save(Educacion educacion){
        rEducacion.save(educacion);
    }

    public void delete(int id){
        rEducacion.deleteById(id);
    }

    public boolean existsById(int id){
        return rEducacion.existsById(id);
    }

    public boolean existsByEduNombre(String eduNombre){
        return rEducacion.existsByEduNombre(eduNombre);
    }


}
