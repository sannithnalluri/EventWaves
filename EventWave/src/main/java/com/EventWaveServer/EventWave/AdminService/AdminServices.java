package com.EventWaveServer.EventWave.AdminService;

import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import com.EventWaveServer.EventWave.UserRepository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServices {

    @Autowired
    private  UserRepo userRepo;
    public List<UserEntity> getAll(){
        return userRepo.findAll();
    }

    public boolean RegisteAmdin(UserEntity user){
        return true;
    }


}
