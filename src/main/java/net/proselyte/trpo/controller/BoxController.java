package net.proselyte.trpo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.proselyte.trpo.dto.BoxDTO;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.entity.Box;
import net.proselyte.trpo.service.BoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxes")
@RequiredArgsConstructor
public class BoxController {

    private final  BoxService boxService;

    @GetMapping("/")
    public ResponseEntity<List<BoxDTO>> getBoxes(){
        return ResponseEntity.ok(boxService.getAllBoxes());
    }

    @GetMapping("/{boxId}")
    //  @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<BoxDTO> getBoxById(@PathVariable Integer boxId){
        return ResponseEntity.ok(boxService.getBoxById(boxId));
    }

    @DeleteMapping("/{boxId}")
    //   @PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<BoxDTO> deleteClient(@PathVariable Integer boxId){
        boxService.deleteBox(boxId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/")
    //@PreAuthorize("hasAuthority('users:write')")
    public ResponseEntity<BoxDTO> addBox(@Valid @RequestBody BoxDTO boxDTO){
        return ResponseEntity.ok(boxService.saveBox(boxDTO));
    }

    @PostMapping("/{boxId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BoxDTO> changeBox(@PathVariable Integer boxId,
                                                 @Valid @RequestBody BoxDTO boxDTO){
        return ResponseEntity.ok(boxService.changeBox(boxId, boxDTO));
    }
}
