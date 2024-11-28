package net.proselyte.trpo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxDTO {

    private Integer id;

    @Min(0)
    private Integer price;

    @NotNull
    @Positive
    private BigDecimal weight;

    @NotNull
    private Boolean is_occupied;
}
