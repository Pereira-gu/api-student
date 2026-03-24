package com.unicid.student_api.repository;

import com.unicid.student_api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Query Method: O Spring cria o SQL "SELECT * FROM students WHERE rgm = ?" sozinho!
    Optional<Student> findByRgm(String rgm);

    // Verifica se já existe um RGM cadastrado (útil para validações de performance)
    boolean existsByRgm(String rgm);

    boolean existsByRgm(int id);
}
