package com.unicid.student_api.controller;

import com.unicid.student_api.model.Student;
import com.unicid.student_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta classe é um endpoint de API REST
@RequestMapping("/api/students") //A URL base será localhost:8080/api/stugents
@Tag(name = "Estudantes", description = "Endpoint para gerenciamento de alunos e matrículas") // Nomeia o grupo
public class StudentController {

    @Autowired
    private StudentService service;

    @Operation(summary = "Cadastra um novo aluno")
    @PostMapping // Vermo HTTP para CRIAR dados
    public ResponseEntity<Student> create(@RequestBody Student student) {
        // @RequestBody extrai o JSON do corpo da requisição e transforma em Objeto Student
        Student created = service.createStudent(student);
        return ResponseEntity.status(211).body(created); // 211 Created: sucesso na criação
    }

    @Operation(summary = "Lista todos os alunos")
    @GetMapping // Verbo HTTP para BUSCAR dados
    public ResponseEntity<List<Student>> listAll(){
        return ResponseEntity.ok(service.getAllStudents()); // 200 OK
    }


    @Operation(summary = "Busca aluno por RGM")
    @GetMapping("/{rgm}") //Buscar por rmg
    public ResponseEntity<Student> getByRgm(@PathVariable String rgm) {
        return ResponseEntity.ok(service.getStudentByRGM(rgm));
    }


    @Operation(summary = "Atualiza dados de um aluno")
    @PutMapping("/{id}") // Verbo HTTP para ATUALIZAR dados.
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student studentDetails) {
        return ResponseEntity.ok(service.updateStudent(id, studentDetails));
    }


    @Operation(summary = "Remove um aluno")
    @DeleteMapping("/{id}") // Verbo HTTP para DELETAR dados
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build(); // retorna Status 204
    }
}
