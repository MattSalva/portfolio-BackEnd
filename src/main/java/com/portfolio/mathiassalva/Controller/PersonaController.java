package com.portfolio.mathiassalva.Controller;

import com.portfolio.mathiassalva.Entity.Persona;
import com.portfolio.mathiassalva.Interface.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://frontend-mathiassalva.web.app")
public class PersonaController {
    @Autowired
    private IPersonaService iPersonaService;


    @GetMapping("/personas/show")
    @ResponseBody
    public List<Persona> getPersona(){
        return iPersonaService.getPersona();
    }

    @GetMapping("/personas/show/profile")
    public Persona findPersona(){
        return iPersonaService.findPersona((long)1);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/create")
    public String createPersona(@RequestBody Persona persona){
        iPersonaService.savePersona(persona);
        return "La persona fue creada con exito";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/delete/{id}")
    public String deletePersona(@PathVariable Long id) {
        iPersonaService.deletePersona(id);
        return "La persona fue borrada con exito";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editarPersona(@PathVariable Long id,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("img") String nuevaImg) {

        Persona persona = iPersonaService.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevaImg);
        iPersonaService.savePersona(persona);
        return persona;
    }
}
