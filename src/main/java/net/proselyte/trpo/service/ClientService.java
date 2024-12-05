package net.proselyte.trpo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.entity.Client;
import net.proselyte.trpo.entity.Reservation;
import net.proselyte.trpo.exceptions.IsNotAvailableException;
import net.proselyte.trpo.exceptions.NoSuchException;
import net.proselyte.trpo.mapper.ClientMapper;
import net.proselyte.trpo.repository.ClientRepository;
import net.proselyte.trpo.repository.ReservationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    private final ReservationRepository reservationRepository;

    @Transactional
    public List<ClientDTO> getAllClients() {
        log.info("Get all Clients");
        if(clientRepository.findAll().isEmpty()){
            throw new NoSuchException("No clients");
        }
        return clientRepository.findAll().stream().map(clientMapper :: toDto).collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO getClientById(Integer clientId){
        log.info("Get client by id: {} ", clientId);
        Optional<Client> clientOptional = Optional.ofNullable(clientRepository.findById(clientId)
                .orElseThrow(()->new NoSuchException("There is no client with ID = " + clientId + " in DB")));
        return clientMapper.toDto(clientOptional.get());
    }


    @Transactional
    public Client registerClient(Client client) {
        // Проверка, существует ли уже пользователь с таким email
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new NoSuchException("User with email " + client.getEmail() + " already exists.");
        }

        // Хеширование пароля
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        // Сохранение пользователя
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(Integer clientId) {
        log.info("Delete client");
        if(clientRepository.findById(clientId).isEmpty()){
            throw new NoSuchException("There is no client with ID = "+ clientId + " in Database");
        }
        Reservation reservation = reservationRepository.findByClientId(clientId);
        if(reservation == null){
            clientRepository.deleteById(clientId);
        }else{
            throw new IsNotAvailableException("Detected reservation, client can`t be deleted");
        }

    }

    @Transactional
    public ClientDTO changeClient(Integer clientId, ClientDTO clientDTO){
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(optionalClient.isEmpty()){
            throw new NoSuchException("There is no client with ID = "+ clientId + " in Database");
        }else{
            Client existingClient = optionalClient.get();
            Client updatedClient = clientMapper.toEntity(clientDTO);
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setPassword(updatedClient.getPassword());
            existingClient.setLastname(updatedClient.getLastname());
            existingClient.setStatus(updatedClient.getStatus());
            existingClient.setRole(updatedClient.getRole());

            return clientMapper.toDto(clientRepository.save(existingClient));
        }
    }

    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("Saving client: {}", clientDTO);
        Client savedClient = clientRepository.save(clientMapper.toEntity(clientDTO));
        return clientMapper.toDto(savedClient);
    }
}
