package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import cichecki.kacper.jsonflattener.persistence.dao.JsonRecordRepository;
import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.persistence.model.JsonRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import cichecki.kacper.jsonflattener.persistence.model.User;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class JsonPersistenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JsonRecordRepository jsonRecordRepository;

    public void saveResult(JsonInput jsonInput, User activeUser) {

        JsonRecord jsonRecord = new JsonRecord(jsonInput);

        User user = userRepository.findByEmail(activeUser.getEmail());
        jsonRecord.setUser(user);

        jsonRecordRepository.save(jsonRecord);
/*
        user = userRepository.save(user);
        log.info(String.valueOf(user));*/
    }

    public void dleteJson(Long jsonId) {
        log.info("deleting json record with Id: " + jsonId);
        jsonRecordRepository.deleteById(jsonId);
    }

    public List<JsonRecord> getJsonRecords(User activeUser) {

        User user  = userRepository.findByEmail(activeUser.getEmail());
        return user.getJsonRecords();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<JsonRecord> getAllJsons() {
        // todo:retreive all json from db
        return Arrays.asList(new JsonRecord());
    }

}
