package net.javaguides.springboot.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	  @ExceptionHandler(ResourceNotFoundException.class)
	    //handle specific exception in controller layer
	    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
	    		                                                            ResourceNotFoundException exception,WebRequest webRequest){
	    	ErrorDetails errorDetails=new ErrorDetails(
	    			LocalDateTime.now(), 
	    			exception.getMessage(), webRequest.getDescription(false), "User Not Found");
	    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	    }
	  
	  @ExceptionHandler(EmailAlreadyExistException.class)
	    //handle specific exception in controller layer
	    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(
	    		                                                     EmailAlreadyExistException exception,WebRequest webRequest){
	    	ErrorDetails errorDetails=new ErrorDetails(
	    			LocalDateTime.now(), 
	    			exception.getMessage(), webRequest.getDescription(false), "User_Email_Already_Exist");
	    	return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	    }
	  
	  @ExceptionHandler(Exception.class)
	    //handle global exception 
	    public ResponseEntity<ErrorDetails> handleException(
	    		                                                     Exception exception,WebRequest webRequest){
	    	ErrorDetails errorDetails=new ErrorDetails(
	    			LocalDateTime.now(), 
	    			exception.getMessage(), webRequest.getDescription(false), "Internal_Server_Error");
	    	return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
