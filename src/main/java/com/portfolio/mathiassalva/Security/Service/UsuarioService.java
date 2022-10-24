package com.portfolio.mathiassalva.Security.Service;


import com.portfolio.mathiassalva.Security.Entity.Usuario;
import com.portfolio.mathiassalva.Security.Repository.IRolRepository;
import com.portfolio.mathiassalva.Security.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    IUsuarioRepository IusuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return IusuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public boolean existsByNombreUsuario(String nombreUsuario){
        return IusuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return IusuarioRepository.existsByEmail(email);
    }

    public void save(Usuario usuario){
        IusuarioRepository.save(usuario);
    }
}
