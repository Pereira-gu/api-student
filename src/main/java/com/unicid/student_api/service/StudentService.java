package com.unicid.student_api.service;

import com.unicid.student_api.model.Student;
import com.unicid.student_api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Transient
    public Student createStudent(Student student) {
        if (repository.existsByRgm(student.getRgm())){
            throw new RuntimeException("Erro: Já existe um aluno cadastrado com este RGM");
        }

        return repository.save(student);
    }

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Student getStudentByRGM(String rgm) {
        return repository.findByRgm(rgm)
                .orElseThrow(()->new RuntimeException("Aluno com RGM" + rgm + "Não encontrado"));
    }
}
