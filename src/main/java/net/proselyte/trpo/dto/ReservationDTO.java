package net.proselyte.trpo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.trpo.entity.Box;
import net.proselyte.trpo.entity.Client;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Integer id;

    private Integer clientId;

    private Integer boxId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
