package net.proselyte.trpo.controller;

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
    @PreAuthorize("hasAuthority('clients:write')")
    public String getClientsPage(){
        return "clients";
    }

    @GetMapping("/clients/{clientId}")
    @PreAuthorize("hasAuthority('clients:write')")
    public String getClientPage(){
        return "client";
    }

    @GetMapping("/clients/change/{clientId}")
    @PreAuthorize("hasAuthority('clients:write')")
    public String getClientChangePage(){
        return "changeClient";
    }

    @GetMapping("/boxes")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getBoxesPage(){
        return "boxes";
    }
    @GetMapping("/boxes/{boxesId}")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getBoxPage(){
        return "box";
    }

    @GetMapping("/boxes/change/{boxesId}")
    @PreAuthorize("hasAuthority('clients:write')")
    public String getChangeBoxPage(){
        return "changeBox";
    }

    @GetMapping("/boxes/create")
    @PreAuthorize("hasAuthority('clients:write')")
    public String getCreateBoxPage(){
        return "createBox";
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getReservationsPage(){
        return "reservations";
    }

    @GetMapping("/reservations/{reservationId}")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getReservationPage(){
        return "reservation";
    }

    @GetMapping("/reservations/change/{reservationId}")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getChangeReservationPage(){
        return "changeReservation";
    }
    @GetMapping("/reservations/create")
    @PreAuthorize("hasAuthority('clients:read')")
    public String getCreateReservationPage(){
        return "createReservation";
    }

    @GetMapping("/register")
    public String registration(){
        return "register";
    }
}

