package net.fiap.postech.fastburger.adapters.configuration.exceptionHandler;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class InterceptorHandlerException extends ResponseEntityExceptionHandler {
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<HandlerBodyFields> campos = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String mensage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            campos.add(new HandlerBodyFields(name, mensage));
        }
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(status.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle("Existem campos invalidos! Verifique e tente novamente.");
        handlerBodyException.setFieldsList(campos);
        return handleExceptionInternal(ex, handlerBodyException, headers, status, request);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(ClientNotFoundException ex, WebRequest request) {
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(HttpStatus.NOT_FOUND.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, handlerBodyException, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNaoEncontrada(ProductNotFoundException ex, WebRequest request) {
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(HttpStatus.NOT_FOUND.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, handlerBodyException, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleNegocio(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(status.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, handlerBodyException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleNegocio(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(status.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, handlerBodyException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleVioletionException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        HandlerBodyException handlerBodyException = new HandlerBodyException();
        handlerBodyException.setStatus(status.value());
        handlerBodyException.setDateTime(OffsetDateTime.now());
        handlerBodyException.setTitle(ex.getMessage());
        return handleExceptionInternal(ex, handlerBodyException, new HttpHeaders(), status, request);
    }

}

