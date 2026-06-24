package com.heyhey.backend.repository;

import com.heyhey.backend.model.TareasEnviadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasEnviadasRepository extends JpaRepository<TareasEnviadas, Integer> {
}
