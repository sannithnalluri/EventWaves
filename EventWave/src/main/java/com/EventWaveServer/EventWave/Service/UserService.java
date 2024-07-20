package com.EventWaveServer.EventWave.Service;


import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import com.EventWaveServer.EventWave.UserRepository.UserRepo;
import com.mongodb.DuplicateKeyException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.status.StatusLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
        @Autowired
        private UserRepo userRepo;

        private PasswordEncoder passwordEncoder;

 private  static Logger logger = LoggerFactory.getLogger(StatusLogger.Config.class);

    public  Boolean Register(UserEntity user){
        logger.info("Attempting to register user with username: {}", user.getUsername());
        UserEntity Existing = userRepo.findByusername(user.getUsername());
        if(Existing != null){
           logger.info("User Already Existid",Existing.getUsername());
           return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepo.save(user);
        } catch (DuplicateKeyException e) {
            logger.warn("Username {} already exists", user.getUsername());
            return false;
        }
        logger.info("User {} successfully registered", user.getUsername());
        return true;
    }
}
