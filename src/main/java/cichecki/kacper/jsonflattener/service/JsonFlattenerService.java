package cichecki.kacper.jsonflattener.service;

import cichecki.kacper.jsonflattener.persistence.model.JsonRecord;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.flattener.PrintMode;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JsonFlattenerService {

    public String flatten(String netedJson) {
        String flattenedJson = new JsonFlattener(netedJson).withPrintMode(PrintMode.PRETTY).flatten();
        return flattenedJson;
    }

    public String nestFlattened(String flattenedJson) {
        String nestedJson = new JsonUnflattener(flattenedJson).withPrintMode(PrintMode.PRETTY).unflatten();
        return nestedJson;
    }

}
