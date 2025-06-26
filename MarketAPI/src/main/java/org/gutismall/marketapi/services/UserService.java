package org.gutismall.marketapi.services;

import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.repository.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.*;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional(readOnly = true)
    public UserEntity getUserByAppIdAndEmail(String appId, String email) {
        UserEntity getUser = userRepository.findUserEntityByAppIdAndEmailIs(appId, email);
        if (getUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User not found ");
        }
        return getUser;
    }
    
    public void registerUser(UserEntity user) {
        UserEntity registeredUser = userRepository.findUserEntityByAppIdAndEmailIs(user.getAppId(), user.getEmail());
        if (registeredUser == null){
            registeredUser = new UserEntity(user.getAppId(),user.getEmail());
            userRepository.save(registeredUser);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User already exists with email: " + user.getEmail());
        }
    }
}
