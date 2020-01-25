package cichecki.kacper.jsonflattener.model;

import lombok.Data;

@Data
public class JsonInput {
    private long id;
    private String nested;
    private String flatten;
}
