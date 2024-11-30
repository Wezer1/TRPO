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

    private Integer client_id;

    private Integer box_id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
