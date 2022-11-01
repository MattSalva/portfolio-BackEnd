package com.portfolio.mathiassalva.Repository;

import com.portfolio.mathiassalva.Entity.Educacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface REducacion extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByEduNombre(String eduNombre);
    public boolean existsByEduNombre(String eduNombre);
}
