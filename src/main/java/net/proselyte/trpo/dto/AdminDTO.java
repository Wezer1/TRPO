package net.proselyte.trpo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.trpo.model.Role;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private Integer id;

    @NotBlank
    private String lastname;

    @NotBlank
    private String password;

    private Role role;
}
