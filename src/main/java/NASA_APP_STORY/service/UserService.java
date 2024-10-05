package NASA_APP_STORY.service;

import NASA_APP_STORY.entity.UserEntity;

public interface UserService {

    UserEntity getUserSettings(String username);

    void createUser(UserEntity userEntity);

    UserEntity findByUsername(String username);

    UserEntity getUserById(Long userId);

    UserEntity getUserByUsername(String username);
}
