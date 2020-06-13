package cichecki.kacper.jsonflattener.persistence.model;

import cichecki.kacper.jsonflattener.dto.JsonInput;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "json_record")
public class JsonRecord {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String flattendeJson;

    private String nestedJson;

    private Date creationTime;

    private Date updateTime;

    @ManyToOne()
    private User user;


    public JsonRecord() {
    }

    public JsonRecord(JsonInput jsonInput) {
        this.flattendeJson = jsonInput.getFlatten();
        this.nestedJson = jsonInput.getNested();
        this.creationTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlattendeJson() {
        return flattendeJson;
    }

    public void setFlattendeJson(String flattendeJson) {
        this.flattendeJson = flattendeJson;
    }

    public String getNestedJson() {
        return nestedJson;
    }

    public void setNestedJson(String nestedJson) {
        this.nestedJson = nestedJson;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
