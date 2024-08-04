package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class CodeResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String uuid;

    private String code;
    private LocalDateTime date;
    private int time;
    private int views;
    @JsonIgnore
    private int initialViews;
    @JsonIgnore
    private int initialTime;

    public CodeResponse() {
        this.uuid = UUID.randomUUID().toString();
    }

    public CodeResponse(String code, int time, int views) {
        this();
        this.code = code;
        this.date = LocalDateTime.now();
        this.time = time;
        this.views = views;
        this.initialViews = views;
        this.initialTime = time;
    }

    // Check if the snippet originally had a views restriction
    @JsonIgnore
    public boolean hadViewsRestriction() {
        return initialViews > 0;
    }

    // Check if the snippet originally had a time restriction
    @JsonIgnore
    public boolean hadTimeRestriction() {
        return initialTime > 0;
    }

    // Check if the snippet has any restrictions
    @JsonIgnore
    public boolean isRestricted() {
        return time > 0 || views > 0;
    }

    // Check if the snippet has expired based on time or views
    @JsonIgnore
    public boolean isExpired() {
        if (hasTimeRestriction() && getTime() <= 0) {
            return true;
        }
        if (hasViewsRestriction() && getViews() <= 0) {
            return true;
        }
        return false;
    }

    // Check if the snippet currently has a time restriction
    @JsonIgnore
    public boolean hasViewsRestriction() {
        return views > 0;  // Any non-negative value is a restriction
    }

    // Check if the snippet currently has a time restriction
    @JsonIgnore
    public boolean hasTimeRestriction() {
        return time > 0; // Any non-negative value is a restriction
    }

    // Decrement the number of views
    public void decrementViews() {
        if(views > 0) {
            views--;
        }
    }

    // Get remaining time for time-restricted snippets
    public int getTime() {
        if (hasTimeRestriction()) {
            long elapsedSeconds = ChronoUnit.SECONDS.between(date, LocalDateTime.now());
            return (int) Math.max(0, time - elapsedSeconds);
        }
        return 0;
    }

    // Getters and setters

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonIgnore
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
