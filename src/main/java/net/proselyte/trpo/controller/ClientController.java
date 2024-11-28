package net.proselyte.trpo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/")
    public ResponseEntity<List<ClientDTO>> getClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{clientId}")
    //  @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<ClientDTO> getOrderById(@PathVariable Integer clientId){
        return ResponseEntity.ok(clientService.getClientById(clientId));
    }

    @DeleteMapping("/{clientId}")
    //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ClientDTO> deleteOrder(@PathVariable Integer clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    //@PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }

    @PostMapping("/{userId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ClientDTO> changeUser(@PathVariable Integer userId,
                                                                  @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.changeClient(userId, clientDTO));
    }
}
