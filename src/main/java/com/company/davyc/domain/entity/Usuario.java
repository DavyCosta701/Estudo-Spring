package com.company.davyc.domain.entity;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NotEmpty(message = "{campo.username.obrigatorio}")
    private String username;
    @Column
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;
    @Column
    private boolean admin;

}
