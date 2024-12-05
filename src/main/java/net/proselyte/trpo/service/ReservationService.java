package net.proselyte.trpo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.proselyte.trpo.dto.ReservationDTO;
import net.proselyte.trpo.entity.Box;
import net.proselyte.trpo.entity.Client;
import net.proselyte.trpo.entity.Reservation;
import net.proselyte.trpo.exceptions.IsNotAvailableException;
import net.proselyte.trpo.exceptions.NoSuchException;
import net.proselyte.trpo.mapper.BoxMapper;
import net.proselyte.trpo.mapper.ReservationMapper;
import net.proselyte.trpo.repository.BoxRepository;
import net.proselyte.trpo.repository.ClientRepository;
import net.proselyte.trpo.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BoxRepository boxRepository;
    private final ClientRepository clientRepository;
    private final ReservationMapper reservationMapper;
    private final BoxMapper boxMapper;

//    public List<Box> getAvailableBoxes() {
//        return boxRepository.findAvailableBoxes();
//    }

    @Transactional
    public List<ReservationDTO> getAllReservation() {
        log.info("Get all Reservation");
        if(reservationRepository.findAll().isEmpty()){
            throw new NoSuchException("No reservation");
        }
        return reservationRepository.findAll().stream().map(reservationMapper :: toDto).collect(Collectors.toList());
    }

    @Transactional
    public ReservationDTO getReservationById(Integer reservationId){
        log.info("Get reservation by id: {} ", reservationId);
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(reservationId)
                .orElseThrow(()->new NoSuchException("There is no reservation with ID = " + reservationId + " in DB")));
        return reservationMapper.toDto(reservationOptional.get());
    }

    @Transactional
    public void deleteReservation(Integer reservationId) {
        log.info("Delete reservation");
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(reservationId)
                .orElseThrow(()->new NoSuchException("There is no reservation with ID = " + reservationId + " in DB")));

        Optional<Box> boxOptional = Optional.ofNullable(boxRepository.findById(reservationOptional.get().getBoxId())
                .orElseThrow(()->new NoSuchException("There is no box with ID = " + reservationOptional.get().getBoxId() + " in DB")));
        boxOptional.get().setIs_occupied(false);
        boxRepository.save(boxOptional.get());

        reservationRepository.deleteById(reservationId);
    }

    @Transactional
    public ReservationDTO changeReservation(Integer reservationId, ReservationDTO reservationDTO){
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if(reservationOptional.isEmpty()){
            throw new NoSuchException("There is no reservation with ID = "+ reservationId + " in Database");
        }else{
            Reservation existingReservation = reservationOptional.get();
            Reservation updatedReservation = reservationMapper.toEntity(reservationDTO);
            existingReservation.setEndDate(updatedReservation.getEndDate());

            return reservationMapper.toDto(reservationRepository.save(existingReservation));
        }
    }

    @Transactional
    public ReservationDTO createReservation(ReservationDTO reservationDTO){
// Проверка на валидность клиента
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findById(reservationDTO.getClient_id())
                .orElseThrow(()->new NoSuchException("There is no client with ID = " + reservationDTO.getClient_id() + " in DB")));

        // Проверка на валидность ячейки
        Optional<Box> boxOptional = Optional.ofNullable(boxRepository.findById(reservationDTO.getBox_id())
                .orElseThrow(()->new NoSuchException("There is no box with ID = " + reservationDTO.getBox_id() + " in DB")));

        if (boxOptional.get().getIs_occupied()) {
            throw new IsNotAvailableException("Box with ID " + reservationDTO.getBox_id() + " is not available during the specified time");
        }

        // Создание нового бронирования
        Reservation savedReservation = reservationRepository.save(reservationMapper.toEntity(reservationDTO));

        // Обновление статуса ячейки
        boxOptional.get().setIs_occupied(true);
        boxRepository.save(boxOptional.get());

        // Возврат DTO для фронтенда
        return reservationMapper.toDto(savedReservation);
    }
}
