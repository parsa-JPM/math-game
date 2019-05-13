package ir.codefather.game.handlers;

import ir.codefather.game.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @see "https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f"
 */
@ControllerAdvice
public class ValidationExceptionHandler {


    /**
     * This method will handle validation failed messages and response will be appropriate for us
     *
     * @param ex it's threw exception
     * @return ApiResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse handleException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        Map<String, String> errors = new TreeMap<>();
        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ApiResponse(errors).setErrorCode(HttpStatus.BAD_REQUEST.value()).setErrorMessage("Validation Error");
    }

}
