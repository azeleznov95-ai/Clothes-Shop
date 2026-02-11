    package com.example.clothesshop.exeptions;

    import com.example.clothesshop.dto.ErrorResponseDto;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.time.LocalDateTime;

    @RestControllerAdvice

    public class ExceptionsHandler {
        @ExceptionHandler(ConflictException.class)
        public ResponseEntity<ErrorResponseDto> ConflictExceptionHandle(ConflictException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(409);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(409).body(errorResponse);

        }

        @ExceptionHandler(IncorrectLoginOrPasswordException.class)
        public ResponseEntity<ErrorResponseDto> IncorrectLoginOrPasswordExceptionHandle(IncorrectLoginOrPasswordException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(401);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(401).body(errorResponse);

        }

        @ExceptionHandler(EmptyUserException.class)
        public ResponseEntity<ErrorResponseDto> EmptyUserHandle(EmptyUserException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(400);
            return ResponseEntity.status(400).body(errorResponse);

        }

        @ExceptionHandler(EmptyCategoryException.class)
        public ResponseEntity<ErrorResponseDto> EmptyCategoryHandle(EmptyCategoryException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(400);
            return ResponseEntity.status(400).body(errorResponse);

        }

        @ExceptionHandler(IdIsInvalidException.class)
        public ResponseEntity<ErrorResponseDto> IdIsInvalidHandle(IdIsInvalidException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setStatus(400);
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }

        @ExceptionHandler(BadClothesRequestException.class)
        public ResponseEntity<ErrorResponseDto> badClothRequestHandle(BadClothesRequestException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setStatus(400);
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }

        @ExceptionHandler(NewsNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> NewsNotFoundHandle(NewsNotFoundException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setStatus(404);
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(404).body(errorResponse);
        }

        @ExceptionHandler(BadNewsRequestException.class)
        public ResponseEntity<ErrorResponseDto> badNewsRequestHandle(BadNewsRequestException ex) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setStatus(400);
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(400).body(errorResponse);
        }
    }
