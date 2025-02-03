package com.mysite.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

// 모든 컨트롤러에 적용된다.
@ControllerAdvice
public class UserNotFoundAdvice {

    //UserNotFoundException 발생 시 여기서 처리한다.
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> exceptionHandler(UserNotFoundException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", ex.getMessage());
        return map;
    }

}
