package net.proselyte.trpo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
