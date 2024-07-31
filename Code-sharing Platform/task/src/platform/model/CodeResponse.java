package platform.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeResponse {
    private final String code;
    private final String date;

    public CodeResponse(String code) {
        this.code = code;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }
}
