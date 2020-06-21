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

    public void saveResult(JsonInput jsonInput) {

        JsonRecord jsonRecord = new JsonRecord(jsonInput);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user = userRepository.findByEmail(user.getEmail());
        user.getJsonRecords().add(jsonRecord);

        userRepository.save(user);
    }

    public void dleteJson(Integer jsonId) {

        log.info("deleting json record");
    }

    public List<JsonRecord> getJsonRecords() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        user = userRepository.findByEmail(user.getEmail());
        return user.getJsonRecords();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<JsonRecord> getAllJsons() {
        // todo:retreive all json from db
        return Arrays.asList(new JsonRecord());
    }

}
