package co.com.cidenet.hulkStore.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import co.com.cidenet.hulkStore.domains.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

}
