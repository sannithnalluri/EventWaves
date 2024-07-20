package com.EventWaveServer.EventWave.AdminController;

import com.EventWaveServer.EventWave.AdminService.AdminServices;
import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin")

public class Controller {
    @Autowired
    private AdminServices adminServices;

    @GetMapping("get_all")
    @PreAuthorize("/Admin")
    public ResponseEntity<List<UserEntity>> get_all_userDetails(){
        List<UserEntity> response = adminServices.getAll();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
