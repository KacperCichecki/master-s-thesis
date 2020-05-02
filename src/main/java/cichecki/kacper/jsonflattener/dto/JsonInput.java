package cichecki.kacper.jsonflattener.dto;

import lombok.Data;

@Data
public class JsonInput {
    private long id;
    private String nested;
    private String flatten;
}
