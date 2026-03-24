package com.unicid.student_api.service;

import com.unicid.student_api.model.Student;
import com.unicid.student_api.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // se ao criar o aluno, verifica se nao existe aluno ja registrado com o RGM
    @Transactional
    public Student createStudent(Student student) {
        if (repository.existsByRgm(student.getRgm())){
            throw new RuntimeException("Erro: Já existe um aluno cadastrado com este RGM");
        }
        // INSERT aluno
        return repository.save(student);
    }

    // busca todos os alunos
    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    //busca o aluno por rgm
    public Student getStudentByRGM(String rgm) {
        return repository.findByRgm(rgm)
                .orElseThrow(()->new RuntimeException("Aluno com RGM" + rgm + "Não encontrado"));
    }

    @Transactional
    public Student updateStudent(Long id, Student studentDetails) {
        //busca o aluno atul ou lança erro se não existir
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alino com ID" + id + "não encontrado!"));
        //atualiza apenas os campos permitidos
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setRgm(studentDetails.getRgm());

        //O save() entende que, como o objeto já tem ID, ele deve fazer um UPDATE e não um INSERT
        return repository.save(student);

    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possivel deletar: Aluno com ID "+ id + "não encontrado");
        }
        repository.deleteById(id);
    }
}
