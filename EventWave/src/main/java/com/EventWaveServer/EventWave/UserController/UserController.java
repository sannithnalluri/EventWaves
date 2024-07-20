package com.EventWaveServer.EventWave.UserController;


import com.EventWaveServer.EventWave.EntityClass.LoginEnity;
import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import com.EventWaveServer.EventWave.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<?> testServer(){
        return new ResponseEntity<>("server running Succesfully", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity){
        boolean response = userService.Register(userEntity);
        if(response){
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        else {
            return  new ResponseEntity<>("User Already Exisited",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public  ResponseEntity<?> Login(){
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
}
