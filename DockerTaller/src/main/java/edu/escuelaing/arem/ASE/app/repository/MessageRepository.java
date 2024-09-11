package edu.escuelaing.arem.ASE.app.repository;

import edu.escuelaing.arem.ASE.app.repository.mesagge.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {
    @Query("{log:'?0'}")
    Message findItemByLog(String name);
}
