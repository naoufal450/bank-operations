package com.octo.bankoperations.web;

import com.octo.bankoperations.dto.CordaDDRObligationDTO;
import com.octo.bankoperations.dto.ObligationRequestDTO;
import com.octo.bankoperations.dto.ObligationUpdateDTO;
import com.octo.bankoperations.enums.StateStatus;
import com.octo.bankoperations.exceptions.ObligationNotFoundException;
import com.octo.bankoperations.service.ObligationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/obligations/redeems")
public class RedeemController {
    @Autowired
    private ObligationService obligationService;

    @GetMapping
    public ResponseEntity<List<CordaDDRObligationDTO>> getAll(@RequestParam(value = "status", defaultValue = "all") String status) {
        return ResponseEntity.ok(obligationService.loadAllRedeems(StateStatus.valueOf(status.toUpperCase())));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CordaDDRObligationDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(obligationService.findRedeemByExternalId(id).orElseThrow(() -> new ObligationNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<String> request(@Valid @RequestBody ObligationRequestDTO request) {
        String signedTx = obligationService.createRedeem(request.getAmount()).orElse("");
        return ResponseEntity.status(HttpStatus.CREATED).body(signedTx);
    }

    @PostMapping("cancel")
    public ResponseEntity<String> cancelRedeem(@Valid @RequestBody ObligationUpdateDTO request) {
        String signedTx = obligationService.cancelRedeem(request.getExternalId()).orElse("");
        return ResponseEntity.status(HttpStatus.OK).body(signedTx);
    }

    @PostMapping("deny")
    public ResponseEntity<String> denyRedeem(@Valid @RequestBody ObligationUpdateDTO request) {
        String signedTx = obligationService.denyRedeem(request.getExternalId()).orElse("");
        return ResponseEntity.status(HttpStatus.OK).body(signedTx);
    }

    @PostMapping("approve")
    public ResponseEntity<String> approveRedeem(@Valid @RequestBody ObligationUpdateDTO request) {
        String signedTx = obligationService.approveRedeem(request.getExternalId()).orElse("");
        return ResponseEntity.status(HttpStatus.OK).body(signedTx);
    }
}
