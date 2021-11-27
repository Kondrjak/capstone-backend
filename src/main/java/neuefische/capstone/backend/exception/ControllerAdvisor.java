package neuefische.capstone.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.NoSuchElementException;

/**
 * Exceptions thrown at any controller will be handled by functions defined in this class in order to prevent
 * httpStatus 500 default response. Status 500 telling server crashed is inappropriate in most cases.
 *
 * @annotation  RestControllerAdvice - catches all controller exceptions and redirects them to be handled here
 *                                   - https://www.bezkoder.com/spring-boot-restcontrolleradvice/
 */
@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /**
     * First exception handler in this class has highest priority
     * @param e -
     * @return ApiError: Ressource not found
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private LogAndResponse noSuchElementException(NoSuchElementException e) {
        return new LogAndResponse("Ressource not found!", e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private LogAndResponse illegalArgumentException(IllegalArgumentException e) {
        return new LogAndResponse( "Given input is not processable!", e);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private LogAndResponse badCredentialsException(BadCredentialsException e) {
        return new LogAndResponse("Username and/or password are not valid!", e);
    }

    /**
     * Last exception handler in this class handles all other throwable
     * @param t - throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private LogAndResponse unknownThrowable(Throwable t) {
        return new LogAndResponse("Exception of unknown behaviour!", t);
    }

}
