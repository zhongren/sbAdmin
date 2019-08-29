package com.example.demo.common.exception;

import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.exception.AuthException;
import com.example.demo.common.exception.exception.BaseException;
import com.example.demo.common.util.JsonUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	//private JsonSerializer jsonSerializer = new JsonSerializer().filterNullValues() ;

	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity<String> handleArgumentNotValidException(MethodArgumentNotValidException exp ){
		FieldError error = exp.getBindingResult().getFieldErrors().get(0) ;
		ResultDto result = new ResultDto(
				ResultDto.FAIL , error.getDefaultMessage() , error.getField() ) ;
		return new ResponseEntity<>( JsonUtil.objectToJson( result ) , HttpStatus.OK  ) ;
	}

	@ExceptionHandler( BaseException.class )
	public ResponseEntity<String> handleBusinessException(BaseException exp ){
		ResultDto result = new ResultDto( exp.getCode() , exp.getMessage() ) ;
		return  new ResponseEntity<>(JsonUtil.objectToJson(result), HttpStatus.OK  ) ;
	}

	@ExceptionHandler( AuthException.class )
	public ResponseEntity<String> handleAuthInvalidException(AuthException exp ){
		ResultDto result = new ResultDto( exp.getCode() , exp.getMessage() ) ;
		return  new ResponseEntity<>( JsonUtil.objectToJson( result ) , HttpStatus.OK  ) ;
	}

	@ExceptionHandler( RuntimeException.class )
	public ResponseEntity<String> handleRuntimeException(RuntimeException exp ){
		ResultDto result = new ResultDto( ResultDto.FAIL, exp.getMessage() ) ;
		return new ResponseEntity<>( JsonUtil.objectToJson( result ) , HttpStatus.INTERNAL_SERVER_ERROR ) ;
	}
}
