package NASA_APP_STORY.service;

import NASA_APP_STORY.entity.UserEntity;

import java.util.Set;

public interface AuthService {

    void registerUser(String username, String password);


    String loginUser(String username, String password);


    UserEntity getUserByUsername(String username);
}