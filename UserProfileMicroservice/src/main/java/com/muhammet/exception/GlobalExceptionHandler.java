package com.muhammet.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

import static com.muhammet.exception.ErrorType.BAD_REQUEST_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handlerRuntimeException(RuntimeException exception){
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        return  new ResponseEntity<>(createMessage(errorType,exception),errorType.getHttpStatus());
    }

    /**
     * ExceptionHandler -> kendisine verilen Exception sınıfını dinlemeye başlar var eğer
     * ilgili sınıf hata fırlatırsa onu yakalar.
     *
     */
    @ExceptionHandler(UserProfileException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerSatisException(UserProfileException satisException){
        return  new ResponseEntity<>(createMessage(satisException.getErrorType(),satisException), satisException.getErrorType().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> fields = new ArrayList<>();
        exception
                .getBindingResult()
                .getFieldErrors().forEach(x->fields.add(x.getField()+ ": "+ x.getDefaultMessage()));
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(9999)
                .message("Girilen bilgiler eksik ya da yanlıştır.")
                .fields(fields)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JDBCConnectionException.class)
    public final ResponseEntity<ErrorMessage> handleJDBCConnectionException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createMessage(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createMessage(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {

        ErrorType errorType = BAD_REQUEST_ERROR;
        return new ResponseEntity<>(createMessage(errorType, exception), errorType.getHttpStatus());
    }


    private ErrorMessage createMessage(ErrorType errorType, Exception exception) {
        log.error("Tüm Hataların geçtiği ortak nokta: " + exception);
        return ErrorMessage.builder()
                .message(errorType.getMessage())
                .code(errorType.getCode())
                .build();
    }


}
