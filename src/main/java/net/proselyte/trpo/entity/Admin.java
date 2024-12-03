package net.proselyte.trpo.entity;

import jakarta.persistence.*;
import lombok.Data;
import net.proselyte.trpo.model.Role;

@Entity
@Table(name = "admins")
@Data
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lastname", length = 64)
    private String lastname;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "role", length = 20)
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
