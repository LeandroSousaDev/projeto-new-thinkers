package com.example.new_thinkers.repository;

import com.example.new_thinkers.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<UF, Number> {
}
