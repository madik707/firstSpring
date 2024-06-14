package com.example.springdemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission")
    private String permission;

    @Override
    public String getAuthority() {
        return this.permission;
    }
}
