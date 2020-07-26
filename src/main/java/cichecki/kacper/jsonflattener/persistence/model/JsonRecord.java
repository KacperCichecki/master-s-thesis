package cichecki.kacper.jsonflattener.persistence.model;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "json_record")
public class JsonRecord {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flattenedJson;

    private String nestedJson;

    private LocalDateTime creationTime;

    private LocalDateTime updateTime;

    @ManyToOne()
    private User user;

    public JsonRecord(JsonInput jsonInput) {
        this.flattenedJson = jsonInput.getFlatten();
        this.nestedJson = jsonInput.getNested();
    }

    @PrePersist
    public void setCreationTime() {
        creationTime = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdateTime() {
        updateTime = LocalDateTime.now();
    }

}
