package co.com.cidenet.hulkStore.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cidenet.hulkStore.dto.TransactionDTO;
import co.com.cidenet.hulkStore.exceptions.ControlledException;
import co.com.cidenet.hulkStore.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<List<TransactionDTO>> getTransactions() throws ControlledException {
        ResponseEntity<List<TransactionDTO>> responseObject;
        List<TransactionDTO> transactionList = transactionService.findAll();
        responseObject = new ResponseEntity<List<TransactionDTO>>(transactionList, HttpStatus.OK);
        return responseObject;
    }
}
