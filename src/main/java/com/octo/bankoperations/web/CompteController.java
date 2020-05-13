package com.octo.bankoperations.web;

import com.octo.bankoperations.dto.CompteDTO;
import com.octo.bankoperations.exceptions.CompteNonExistantException;
import com.octo.bankoperations.mapper.CompteMapper;
import com.octo.bankoperations.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/comptes")
public class CompteController {

    private final CompteService compteService;

    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("/exists/{rib}")
    public ResponseEntity<Boolean> ribExists(@PathVariable String rib){
        return ResponseEntity.ok(compteService.existsByRIB(rib));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CompteDTO dto){
        compteService.save(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompteDTO> getById(@PathVariable Long id){
        return ResponseEntity.
                ok(CompteMapper.map((compteService.getById(id).orElseThrow(() -> new CompteNonExistantException(id)))));
    }

    @GetMapping("client/{id}")
    public ResponseEntity<CompteDTO> getByUtilisateurId(@PathVariable Long id){
        return ResponseEntity.
                ok(CompteMapper.map(compteService.getComptesForClient(id).orElseThrow(CompteNonExistantException::new)));
    }
}
