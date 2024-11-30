package net.proselyte.trpo.controller;


import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.dto.ReservationDTO;
import net.proselyte.trpo.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> showAllReservation(){
        return ResponseEntity.ok(reservationService.getAllReservation());
    }

    @GetMapping("/{reservationId}")
    //  @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<ReservationDTO> getClientById(@PathVariable Integer reservationId){
        return ResponseEntity.ok(reservationService.getReservationById(reservationId));
    }

    @PostMapping("/{reservationId}")
    //    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ReservationDTO> changeReservation(@PathVariable Integer reservationId,
                                                  @RequestBody ReservationDTO reservationDTO){
        return ResponseEntity.ok(reservationService.changeReservation(reservationId, reservationDTO));
    }
    @PostMapping("/")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.createReservation(reservationDTO));
    }

    @DeleteMapping("/{reservationId}")
    //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ReservationDTO> deleteClient(@PathVariable Integer reservationId){
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.noContent().build();
    }

}
