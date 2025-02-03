package com.mysite.backend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("id에 맞는 유저가 없습니다." + id);
    }

}
