package desafio.itau.springboot.controllers;

import desafio.itau.springboot.dto.StatisticsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import desafio.itau.springboot.services.TransactionService;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private TransactionService transactionsService;

    public StatisticsController(TransactionService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public ResponseEntity<StatisticsResponse> getMethodName(){
        DoubleSummaryStatistics stats = transactionsService.getStatistics();

        return ResponseEntity.ok(new StatisticsResponse(stats));
    }
}
