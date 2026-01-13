    package com.example.personalfinancetracker.exeptions;

    import com.example.personalfinancetracker.dto.ErrorResponse;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.time.LocalDateTime;

    @RestControllerAdvice
    public class ExceptionsHandle {
        public ResponseEntity<ErrorResponse> ConflictExceptionHandle(ConflictException ex){
            ErrorResponse errorResponse= new ErrorResponse();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(409);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(409).body(errorResponse);

        }
        public ResponseEntity<ErrorResponse>  IncorrectLoginOrPasswordExceptionHandle(IncorrectLoginOrPasswordException ex){
            ErrorResponse errorResponse= new ErrorResponse();
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(401);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.status(401).body(errorResponse);

        }
    }
