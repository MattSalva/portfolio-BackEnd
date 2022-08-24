package com.portfolio.mathiassalva.Service;

import com.portfolio.mathiassalva.Entity.Persona;
import com.portfolio.mathiassalva.Interface.IPersonaService;
import com.portfolio.mathiassalva.Repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpPersonaService implements IPersonaService {
    @Autowired IPersonaRepository iPersonaRepository;

    @Override
    public List<Persona> getPersona() {
        List<Persona> personas = iPersonaRepository.findAll();
        return personas;
    }

    @Override
    public void savePersona(Persona per) {
        iPersonaRepository.save(per);
    }

    @Override
    public void deletePersona(Long id) {
        iPersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona = iPersonaRepository.findById(id).orElse(null);
        return persona;
    }
}
