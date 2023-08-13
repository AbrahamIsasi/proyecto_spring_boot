package com.proyecto.apiRest.repositorio;

import com.proyecto.apiRest.modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepositorio extends JpaRepository<Contacto,Long>{
}
