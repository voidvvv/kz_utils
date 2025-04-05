package com.kz.web.config.handler;

import com.kz.web.config.secure.context.exceptions.AlreadyExistingUser;
import com.kz.web.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(AlreadyExistingUser.class)
    public ResponseDTO handleUserExistsException(AlreadyExistingUser e, HttpServletResponse response) {
        return ResponseDTO.fail("User already exists", e.getMessage(), AlreadyExistingUser.CODE);
    }
}
