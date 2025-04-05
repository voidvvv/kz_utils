package com.kz.web.config.handler;

import com.kz.web.config.secure.context.exceptions.AlreadyExistingUser;
import com.kz.web.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(AlreadyExistingUser.class)
    public ResponseDTO handleUserExistsException(AlreadyExistingUser e, HttpServletResponse response) {
        return ResponseDTO.fail("User already exists", e.getMessage(), AlreadyExistingUser.CODE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO globalException (Exception e) {
        log.info("Global exception", e);
        return ResponseDTO.fail("Global exception", "An error occurred", 500);
    }
}
