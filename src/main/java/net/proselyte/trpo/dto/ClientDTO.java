package net.proselyte.trpo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.trpo.model.Role;
import net.proselyte.trpo.model.Status;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Integer id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String lastname;

    private Role role;

    private Status status;
}
