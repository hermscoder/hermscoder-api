package com.herms.hermscoder.service;

import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import com.herms.hermscoder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileServiceImpl profileService;

    public Optional<User> findByEmail(String email){
        return findByEmail(email, Collections.emptySet());
    }
    @Transactional
    public Optional<User> findByEmail(String email, Set<Class> fetchPolicy){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent() && !fetchPolicy.isEmpty()){
            User user = userOptional.get();
            if(user != null && fetchPolicy.contains(Profile.class)) {
                user.setProfile(profileService.findByUserId(user.getId()));
            }
        }
        return userOptional;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
