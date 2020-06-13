package cichecki.kacper.jsonflattener.persistence.dao;

import cichecki.kacper.jsonflattener.persistence.model.JsonRecord;
import cichecki.kacper.jsonflattener.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonRecordRepository extends JpaRepository<JsonRecord, Long> {

    @Override
    void delete(JsonRecord jsonRecord);

}
