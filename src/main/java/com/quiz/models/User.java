package com.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection (targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable (name = "user_role", joinColumns = @JoinColumn (name = "user_id"))
    @Enumerated (EnumType.STRING)   // чтобы хранить все роли в виде одной строки
    private Set<Role> roles;

}
