package com.octo.bankoperations.web;

import com.octo.bankoperations.dto.BankTransferDTO;
import com.octo.bankoperations.dto.CordaInterBankTransferDTO;
import com.octo.bankoperations.exceptions.TransferNotFoundException;
import com.octo.bankoperations.service.InterBankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/inter")
public class InterBankTransferController {

    @Autowired
    private InterBankTransferService interBankTransferService;

    @GetMapping
    public ResponseEntity<List<CordaInterBankTransferDTO>> getAllInterBankTransfers() {
        return ResponseEntity.ok(interBankTransferService.loadAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CordaInterBankTransferDTO> getInterBankTransfer(@PathVariable String id) {
        return ResponseEntity.ok(interBankTransferService.findById(id).orElseThrow(() -> new TransferNotFoundException(id)));
    }

    @PostMapping(value = "perform-transfer")
    public ResponseEntity<String> performTransfer(@Valid @RequestBody BankTransferDTO dto) {
        String signedTx = interBankTransferService.transfer(dto).orElse("");
        return ResponseEntity.status(HttpStatus.CREATED).body(signedTx);
    }

}
