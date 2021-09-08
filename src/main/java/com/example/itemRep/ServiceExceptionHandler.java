package com.example.itemRep;

import com.example.itemRep.dto.ErrorDto;
import com.example.itemRep.dto.ResponseDto;

import com.example.itemRep.wrapper.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ServiceExceptionHandler {
    private static  final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception ex){
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto(ex.getMessage(),null,"Receiver Exception"));

        ResponseWrapper<List> responseWrapper =  new ResponseWrapper<>();
        return responseWrapper.wrapToResponse(() -> responseWrapper.wrap(Arrays.asList(Collections.emptyList()),errors),HttpStatus.BAD_REQUEST);

    }

    /*@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<ResponseDto> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex){
        List<ErrorDto> errors = new ArrayList<>();
        System.out.println("here in incorrect result exception");

        errors.add(new ErrorDto(ex.getMessage(),null,"Reciever"));

        ResponseWrapper<List> responseWrapper = new ResponseWrapper<>();
        return responseWrapper.wrapToResponse(() -> responseWrapper.wrap(Arrays.asList(Collections.emptyList()),errors),HttpStatus.BAD_REQUEST);

    }*/




}
