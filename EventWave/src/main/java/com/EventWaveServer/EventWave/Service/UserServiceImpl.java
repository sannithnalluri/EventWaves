package com.EventWaveServer.EventWave.Service;

import com.EventWaveServer.EventWave.EntityClass.UserEntity;
import com.EventWaveServer.EventWave.UserRepository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername: {}", username);
        UserEntity user = userRepo.findByusername(username);

        if (user == null) {
            logger.error("User not found: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("Admin")
                .build();

        return userDetails;
    }
}
