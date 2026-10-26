package art.vol.b1.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(VolIntrouvableException.class)
    public ProblemDetail handleVolIntrouvableException(VolIntrouvableException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(PlusDePlaceException.class)
    public ProblemDetail handlePlusDePlaceException(PlusDePlaceException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMessages = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorsMessages.put(error.getField(), error.getDefaultMessage());
        });
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Requête échouée");
        problemDetail.setProperty("errors", errorsMessages);
        return problemDetail;
    }

}
