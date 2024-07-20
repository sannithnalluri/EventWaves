package com.EventWaveServer.EventWave.EntityClass;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "userData")
public class UserEntity {
    private  String username;
    private  String email;
    private  String password;
}
