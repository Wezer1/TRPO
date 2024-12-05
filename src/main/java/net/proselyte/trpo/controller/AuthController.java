package net.proselyte.trpo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.entity.Client;
import net.proselyte.trpo.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "success";
    }

    @GetMapping("/clients")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getClientsPage(){
        return "clients";
    }

    @GetMapping("/clients/{clientId}")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getClientPage(){
        return "client";
    }
    @GetMapping("/clients/change/{clientId}")
    @PreAuthorize("hasAuthority('clients:write')")
    public String getClientChangePage(){
        return "changeClient";
    }



    @GetMapping("/register")
    public String registration(){
        return "register";
    }
}

