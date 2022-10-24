package com.portfolio.mathiassalva.Security.Repository;

import com.portfolio.mathiassalva.Security.Entity.Rol;
import com.portfolio.mathiassalva.Security.Enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}


