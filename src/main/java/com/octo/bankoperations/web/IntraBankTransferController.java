package com.octo.bankoperations.web;

import com.octo.bankoperations.dto.BankTransferDTO;
import com.octo.bankoperations.dto.CordaIntraBankTransferDTO;
import com.octo.bankoperations.exceptions.TransferNotFoundException;
import com.octo.bankoperations.service.IntraBankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("api/intra")
public class IntraBankTransferController {

    @Autowired
    private IntraBankTransferService intraBankTransferService;

    @GetMapping
    public ResponseEntity<List<CordaIntraBankTransferDTO>> getAllIntraBankTransfers() {
        return ResponseEntity.ok(intraBankTransferService.loadAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CordaIntraBankTransferDTO> getIntraBankTransfer(@PathVariable String id) {
        return ResponseEntity.ok(intraBankTransferService.findById(id).orElseThrow(() -> new TransferNotFoundException(id)));
    }

    @PostMapping(value = "/record-transfer")
    public ResponseEntity<String> recordTransfer(@Valid @RequestBody BankTransferDTO dto) {
        String signedTx = intraBankTransferService.transfer(dto).orElse("");
        return ResponseEntity.status(HttpStatus.CREATED).body(signedTx);
    }

}
