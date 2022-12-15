package com.LabAPI.laboratoryAPI.repositories;

import com.LabAPI.laboratoryAPI.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRepository extends JpaRepository<Test, Long> {
}
