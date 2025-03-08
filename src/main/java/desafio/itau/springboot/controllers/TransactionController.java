package desafio.itau.springboot.controllers;

import desafio.itau.springboot.dto.TransactionRequest;
import jakarta.validation.Valid;
import desafio.itau.springboot.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import desafio.itau.springboot.services.TransactionService;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request){

        if(request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor() <= 0){
            return ResponseEntity.unprocessableEntity().build();
        }

        transactionService.addTransaction(new Transaction(
                request.getValor(), request.getDataHora()));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
