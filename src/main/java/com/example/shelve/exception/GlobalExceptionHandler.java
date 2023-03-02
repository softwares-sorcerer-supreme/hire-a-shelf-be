package com.example.shelve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<ErrorResponse> handleUserExistedException(UserExistedException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return  errorMap;
    }

//    @ExceptionHandler(com.example.shelve.exception.MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidArgument(com.example.shelve.exception.MethodArgumentNotValidException ex){
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .message("Request not valid")
//                .status(400)
//                .build();
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
//    public String handleBindException(BindException e) {
//        // Trả về message của lỗi đầu tiên
//        String errorMessage = "Request không hợp lệ";
//        if (e.getBindingResult().hasErrors())
//            e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        return errorMessage;
//    }
}
