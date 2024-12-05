package net.proselyte.trpo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.model.Status;
import net.proselyte.trpo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('clients:read')")
    public ResponseEntity<List<ClientDTO>> getClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{clientId}")
    @PreAuthorize("hasAuthority('clients:read')")
    //  @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer clientId){
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }

    @DeleteMapping("/{clientId}")
    @PreAuthorize("hasAuthority('clients:write')")
    //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Integer clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/registration")
    //@PreAuthorize("hasAuthority('clients:write')")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO){
        clientDTO.setStatus(Status.valueOf("ACTIVE"));
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAuthority('clients:write')")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ClientDTO> changeClient(@PathVariable Integer userId,
                                                                  @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.changeClient(userId, clientDTO));
    }
}
