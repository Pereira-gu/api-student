package com.unicid.student_api.controller;

import com.unicid.student_api.model.Student;
import com.unicid.student_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta classe é um endpoint de API REST
@RequestMapping("/api/students") //A URL base será localhost:8080/api/stugents
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping // Vermo HTTP para CRIAR dados
    public ResponseEntity<Student> create(@RequestBody Student student) {
        // @RequestBody extrai o JSON do corpo da requisição e transforma em Objeto Student
        Student created = service.createStudent(student);
        return ResponseEntity.status(211).body(created); // 211 Created: sucesso na criação
    }

    @GetMapping // Verbo HTTP para BUSCAR dados
    public ResponseEntity<List<Student>> listAll(){
        return ResponseEntity.ok(service.getAllStudents()); // 200 OK
    }

    @GetMapping("/{rmg}") //Buscar por rmg
    public ResponseEntity<Student> getByRgm(@PathVariable String rgm) {
        return ResponseEntity.ok(service.getStudentByRGM(rgm));
    }

    @PutMapping("/{id}") // Verbo HTTP para ATUALIZAR dados.
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(service.updateStudent(id, studentDetails));
    }

    @DeleteMapping("/{id}") // Verbo HTTP para DELETAR dados
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build(); // retorna Status 204
    }
}
