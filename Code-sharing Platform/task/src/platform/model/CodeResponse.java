package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeResponse {
    @JsonIgnore
    private long id;
    private String code;
    private String date;

    public CodeResponse(long id, String code) {
        this.id = id;
        this.code = code;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }
}
