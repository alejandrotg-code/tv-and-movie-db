package com.alejandrotg.tv_and_movie_db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;
}
