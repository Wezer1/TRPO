package net.proselyte.trpo.entity;

import jakarta.persistence.*;
import lombok.Data;
import net.proselyte.trpo.model.Role;
import net.proselyte.trpo.model.Status;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


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

    @Column(name = "role", length = 20)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
