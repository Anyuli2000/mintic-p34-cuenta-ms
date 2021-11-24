package com.misiontic.futbolinms.repositories;

import com.misiontic.futbolinms.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TransactionRepository extends MongoRepository <Transaction , String>{
    List<Transaction> findByCuentaOrigen (String cuentaOrigen);
    List<Transaction> findByCuentaDestino (String cuentaDestino);
}
