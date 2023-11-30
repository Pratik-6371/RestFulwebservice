package net.javaguides.springboot.controller;

import net.javaguides.springboot.Exception.ErrorDetails;
import net.javaguides.springboot.Exception.ResourceNotFoundException;
import net.javaguides.springboot.dto.UserDto;

import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController

@RequestMapping("api/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
    	this.userService=userService;
    	
    }

    // build create User REST API
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        var  savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        var user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserDto user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    //handle specific exception in controller layer
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
    		                                                            ResourceNotFoundException exception,WebRequest webRequest){
    	ErrorDetails errorDetails=new ErrorDetails(
    			LocalDateTime.now(), 
    			exception.getMessage(), webRequest.getDescription(false), "User Not Found");
    	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
}
