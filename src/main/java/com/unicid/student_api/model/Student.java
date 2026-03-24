package com.unicid.student_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "students") //Define o nome da tabela no banco
@Data // Gera Getters, Setters, toString, etc... automaticamente
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o nome é obrigatório")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "o RMG é obrigatorio")
    @Column(nullable = false)
    private String rgm;

    @Email(message = "E-mail deve ser válido")
    @NotBlank(message = "O e-mail é obrigatorio")
    @Column(unique = true, nullable = false)
    private String email;


    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreat(){
        this.createdAt = LocalDateTime.now();
    }
}
