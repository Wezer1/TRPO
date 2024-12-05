package net.proselyte.trpo.repository;

import net.proselyte.trpo.entity.Reservation;
import net.proselyte.trpo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByClientId(Integer clientId);
}

