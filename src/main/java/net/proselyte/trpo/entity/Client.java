package net.proselyte.trpo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "lastname", length = 64)
    private String lastname;
}
