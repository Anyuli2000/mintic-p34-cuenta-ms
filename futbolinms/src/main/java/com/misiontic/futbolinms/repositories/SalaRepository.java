package com.misiontic.futbolinms.repositories;

import com.misiontic.futbolinms.models.Sala;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalaRepository extends MongoRepository <Sala, String> {

}
