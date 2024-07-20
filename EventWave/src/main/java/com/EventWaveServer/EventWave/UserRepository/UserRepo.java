package com.EventWaveServer.EventWave.UserRepository;

import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepo  extends MongoRepository<UserEntity,String> {
    UserEntity findByusername(String email);
}
