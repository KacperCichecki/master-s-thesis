package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import cichecki.kacper.jsonflattener.persistence.dao.JsonRecordRepository;
import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.persistence.model.JsonRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import cichecki.kacper.jsonflattener.persistence.model.User;

@Service
public class JsonPersistenceService {

    @Autowired
    private UserRepository userRepository;

    public void saveResult(JsonInput jsonInput) {

        JsonRecord jsonRecord = new JsonRecord(jsonInput);

        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
        user.getJsonRecords().add(jsonRecord);

        userRepository.save(user);

    }
}
