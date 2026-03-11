package com.identity.flow.nicollas.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "user")
@Data // Cria getters, setters, constructors... (é mais completo que só o @Getter e @Setter).
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer userId;

    @NotBlank(message = "O nome é obrigatório!") // Serve para não deixar o campo ser nulo ou string vazia, na camada da aplicação.
    @Column(name = "complete_name", length = 200, nullable = false) // nullable = false é a validação no save do banco de dados (outra camada).
    private String completeName;

    @NotBlank(message = "O nome de usuário é obrigatório!")
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Email(message = "Insira um e-mail válido!") // Validador de e-mail.
    @NotBlank(message = "O email é obrigatório!")
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @NotBlank(message = "A senha é obrigatória!")
    @Column(name = "pass", columnDefinition = "TEXT", nullable = false)
    private String pass;

    @NotBlank(message = "O telefone é obrigatório!")
    @Column(name = "telephone", length = 15, nullable = false)
    private String telephone;

}
