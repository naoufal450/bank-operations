package com.octo.bankoperations.web;

import com.octo.bankoperations.dto.CompteDTO;
import com.octo.bankoperations.exceptions.CompteNonExistantException;
import com.octo.bankoperations.mapper.CompteMapper;
import com.octo.bankoperations.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/comptes")
public class CompteController {

    private final CompteService compteService;

    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping
    public List<CompteDTO> getAll() {
        return Optional.ofNullable(compteService.getAll()).orElse(Collections.emptyList())
                .stream().map(CompteMapper::map).collect(Collectors.toList());
    }

    @GetMapping("/exists/{rib}")
    public ResponseEntity<Boolean> ribExists(@PathVariable String rib) {
        return ResponseEntity.ok(compteService.existsByRIB(rib));
    }

    @PostMapping
    public ResponseEntity<CompteDTO> create(@Valid @RequestBody CompteDTO dto) {
        return new ResponseEntity<>(CompteMapper.map(compteService.save(dto)), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CompteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.
                ok(CompteMapper.map((compteService.getById(id).orElseThrow(() -> new CompteNonExistantException(id)))));
    }

    @GetMapping("client/{id}")
    public ResponseEntity<CompteDTO> getByClientId(@PathVariable Long id) {
        return ResponseEntity.
                ok(CompteMapper.map(compteService.getComptesForClient(id).orElseThrow(CompteNonExistantException::new)));
    }
}
