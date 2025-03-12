package com.example.oci_microsevice.repository;

import com.example.oci_microsevice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByMatricula(String matricula);
}
