package com.misiontic.futbolinms.repositories;

import com.misiontic.futbolinms.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository <Account, String> {
}
