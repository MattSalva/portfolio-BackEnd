package com.portfolio.mathiassalva.Interface;

import com.portfolio.mathiassalva.Entity.Persona;

import java.util.List;

public interface IPersonaService {
    // Get Lista Personas
    public List<Persona> getPersona();

    // Save objeto type persona
    public void savePersona(Persona per);

    // Eliminar objeto persona por ID
    public void deletePersona(Long id);

    // Buscar persona por ID
    public Persona findPersona(Long id);

}