package sharypov.OrganizationRestApi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sharypov.OrganizationRestApi.exception.CommonException;
import sharypov.OrganizationRestApi.model.error.RestError;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.util.Map;
import java.util.Set;


@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
    public static final String EXCEPTION_DUPLICATE_DATETIME = "exception.meal.duplicateDateTime";

    private static final Map<String, String> CONSTRAINS_I18N_MAP = Map.of(
            "users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL,
            "meals_unique_user_datetime_idx", EXCEPTION_DUPLICATE_DATETIME);


    public ExceptionInfoHandler() {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public RestError handleError(HttpServletRequest req, Exception e) {
        return new RestError(req.getRequestURL().toString() + " " + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public RestError handleErrorConstraintViolation(HttpServletRequest req, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder error = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            error.append(violation.getMessage()).append("; ");
        }
        return new RestError(error.toString());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestError handleErrorMethodArgumentNotValid(MethodArgumentNotValidException e) {
        StringBuilder error = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.append(fieldError.getDefaultMessage()).append("; ");
        }
        return new RestError(error.toString());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CommonException.class)
    public RestError handleErrorConvertException(CommonException e) {
        return new RestError(e.getMessage());
    }
}