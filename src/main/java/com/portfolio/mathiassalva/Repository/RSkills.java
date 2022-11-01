package com.portfolio.mathiassalva.Repository;

import com.portfolio.mathiassalva.Entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RSkills extends JpaRepository <Skills, Integer>{
    Optional<Skills> findByNombre(String nombre);
    public Boolean existsByNombre(String nombre);
}
