package com.example.spring_boot_essentials.handler;

import com.example.spring_boot_essentials.exception.ErroResponse;
import com.example.spring_boot_essentials.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================================================
    // 404 - NOT FOUND
    // =========================================================
    // Captura exceptions do tipo NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroResponse> handlerNotFoundException(NotFoundException ex){

        // Cria o objeto de resposta padronizado
        ErroResponse erroResponse = ErroResponse.builder()

                // Mensagem da exception
                .message(ex.getMessage())

                // Código HTTP 404
                .status(HttpStatus.NOT_FOUND.value())

                .build();

        // Retorna resposta HTTP 404 com body JSON
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(erroResponse);
    }

    // =========================================================
    // 400 - BAD REQUEST
    // =========================================================
    // Captura erros de validação de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handlerValidationException(MethodArgumentNotValidException ex){

        // Monta resposta de erro
        ErroResponse erroResponse = ErroResponse.builder()

                // Mensagem personalizada
                .message("Erro de validação nos campos enviados")

                // Código HTTP 400
                .status(HttpStatus.BAD_REQUEST.value())

                .build();

        // Retorna resposta HTTP 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroResponse);
    }

    // =========================================================
    // 415 - UNSUPPORTED MEDIA TYPE
    // =========================================================
    // Captura erro de Content-Type inválido
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErroResponse> handlerMediaTypeException(HttpMediaTypeNotSupportedException ex){

        // Cria objeto de resposta
        ErroResponse erroResponse = ErroResponse.builder()

                // Mensagem explicando o problema
                .message("Content-Type não suportado. Utilize application/json")

                // Código HTTP 415
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())

                .build();

        // Retorna resposta HTTP 415
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(erroResponse);
    }

    // =========================================================
    // 500 - INTERNAL SERVER ERROR
    // =========================================================
    // Captura qualquer exception não tratada anteriormente
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerException(Exception ex){

        // Cria resposta genérica de erro
        ErroResponse erroResponse = ErroResponse.builder()

                // Mensagem da exception
                .message(ex.getMessage())

                // Código HTTP 500
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())

                .build();

        // Retorna resposta HTTP 500
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erroResponse);
    }
}