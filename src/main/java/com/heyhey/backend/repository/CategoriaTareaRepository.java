package com.heyhey.backend.repository;

import com.heyhey.backend.model.CategoriaTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaTareaRepository extends JpaRepository<CategoriaTarea, Integer> {
}
