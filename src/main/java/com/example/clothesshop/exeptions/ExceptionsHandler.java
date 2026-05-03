package com.example.clothesshop.exeptions;

import com.example.clothesshop.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> ConflictExceptionHandle(ConflictException ex) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(IncorrectLoginOrPasswordException.class)
    public ResponseEntity<ErrorResponseDto> IncorrectLoginOrPasswordExceptionHandle(IncorrectLoginOrPasswordException ex) {
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(EmptyUserException.class)
    public ResponseEntity<ErrorResponseDto> EmptyUserHandle(EmptyUserException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EmptyCategoryException.class)
    public ResponseEntity<ErrorResponseDto> EmptyCategoryHandle(EmptyCategoryException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(IdIsInvalidException.class)
    public ResponseEntity<ErrorResponseDto> IdIsInvalidHandle(IdIsInvalidException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadClothesRequestException.class)
    public ResponseEntity<ErrorResponseDto> badClothRequestHandle(BadClothesRequestException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> NewsNotFoundHandle(NewsNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadNewsRequestException.class)
    public ResponseEntity<ErrorResponseDto> badNewsRequestHandle(BadNewsRequestException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadCartRequestException.class)
    public ResponseEntity<ErrorResponseDto> BadCartRequestHandle(BadCartRequestException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> CartNotFoundExceptionHandle(CartNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> UserNotFoundExceptionHandle(UserNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ClothNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> ClothNotFoundExceptionHandle(ClothNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> CategoryNotFoundExceptionHandle(CategoryNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDto> buildError(HttpStatus status, String message) {
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(message);
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(status).body(errorResponse);
    }
}
