package net.proselyte.trpo.mapper;

import net.proselyte.trpo.dto.ReservationDTO;
import net.proselyte.trpo.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ReservationMapper {
    public abstract Reservation toEntity(ReservationDTO reservationDTO);

    public abstract ReservationDTO toDto(Reservation reservation);
}
