package neuefische.capstone.backend.exception;

import lombok.Data;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Response model for the controller advisor
 * plus automatic logging to Slf4j
 */
@Generated
@Data
@Slf4j
public class LogAndResponse {

    private String text;
    private String exceptionMessage;
    private LocalDateTime timeStamp;

    /**
     * Response model for the controller advisor
     * plus automatic logging to Slf4j
     * @param text - String - additional error text
     * @param t - Throwable - most likely an error
     */
    public LogAndResponse(String text, Throwable t) {
        log.error(text,t);
        this.text = "Api Error: "+text;
        this.exceptionMessage = t.getMessage();
        this.timeStamp = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogAndResponse)) return false;
        LogAndResponse that = (LogAndResponse) o;
        return Objects.equals(text, that.text) && Objects.equals(exceptionMessage, that.exceptionMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, exceptionMessage);
    }
}
