package net.proselyte.trpo.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.dto.AdminDTO;
import net.proselyte.trpo.dto.BoxDTO;
import net.proselyte.trpo.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;

    @GetMapping("/")
    public ResponseEntity<List<AdminDTO>> getAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable Integer adminId){
        return ResponseEntity.ok(adminService.getAdmin(adminId));
    }

    @PostMapping("/")
    //@PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<AdminDTO> addBox(@Valid @RequestBody AdminDTO AdminDTO){
        return ResponseEntity.ok(adminService.saveAdmin(AdminDTO));
    }

    @DeleteMapping("/{adminId}")
    //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<AdminDTO> deleteClient(@PathVariable Integer adminId){
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }


}
