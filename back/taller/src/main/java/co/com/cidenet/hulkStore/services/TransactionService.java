package co.com.cidenet.hulkStore.services;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.cidenet.hulkStore.domains.Transaction;
import co.com.cidenet.hulkStore.dto.ProductDTO;
import co.com.cidenet.hulkStore.dto.TransactionDTO;
import co.com.cidenet.hulkStore.repositories.TransactionRepository;
import co.com.cidenet.hulkStore.utils.Utilities;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction(transactionDTO);
        transactionRepository.save(transaction);
    }

    public List<TransactionDTO> findAll() {
        Iterable<Transaction> iterable = transactionRepository.findAll();
        List<Transaction> transactionList = IterableUtils.toList(iterable);
        return Utilities.convertEntityListToDTO(transactionList);
    }

    void createTransaction(ProductDTO productDTO, boolean type, long quantity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setProductDTO(productDTO);
        transactionDTO.setDateCreated(Calendar.getInstance().getTime());
        transactionDTO.setValueTransaction(productDTO.getValue() * Math.abs(quantity));
        transactionDTO.setType(type);
        transactionDTO.setQuantity(Math.abs(quantity));
        saveTransaction(transactionDTO);
    }

}
