package com.heyhey.backend.repository;

import com.heyhey.backend.model.TareasEnviadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareasEnviadasRepository extends JpaRepository<TareasEnviadas, Integer> {

    List<TareasEnviadas> findByEstado(Integer estado);

    List<TareasEnviadas> findByEmpleado_IdEmpleado(Integer idEmpleado);
}
