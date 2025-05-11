package com.kz.web.config.handler;

import com.kz.web.config.secure.context.exceptions.AlreadyExistingUser;
import com.kz.web.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
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

    // ExpiredJwtException
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseDTO handleExpiredJwtException(ExpiredJwtException e, HttpServletResponse response) {
        return ResponseDTO.fail("Token expired", e.getMessage(), 401);
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO globalException (Exception e, HttpServletResponse response, HttpServletRequest request) {

        log.info("Global exception url: {}", request.getRequestURL(), e);
        response.setStatus(500);
        return ResponseDTO.fail("Global exception", "An error occurred", 500);
    }
}
