package com.herms.hermscoder.service;

import com.herms.hermscoder.exception.HermsCoderException;
import com.herms.hermscoder.exception.IncorrectUsernameOrPasswordException;
import com.herms.hermscoder.model.AuthenticationResponse;
import com.herms.hermscoder.model.dto.AuthenticationRequest;
import com.herms.hermscoder.model.dto.ProfileDTO;
import com.herms.hermscoder.model.dto.UserDTO;
import com.herms.hermscoder.model.dto.UserRegistration;
import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import com.herms.hermscoder.repository.UserRepository;
import com.herms.hermscoder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    public void signUp(UserRegistration userRegistration) {

        try {
            userService.findByEmail(userRegistration.getEmail());
        } catch (Exception ex) {
            throw new HermsCoderException("The email " + userRegistration.getEmail() + " is already associated to an existing account.");
        }

        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(encodePassword(userRegistration.getPassword()));
        userService.save(user);

        userRegistration.getProfile().setUser(new UserDTO(user));
        ProfileDTO profileDTO = profileService.save(userRegistration.getProfile());

        user.setProfile(profileDTO.toProfile());
        userService.save(user);

    }

    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }


    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new IncorrectUsernameOrPasswordException("Incorrect username or password", e);
        }
        final User user = userService.findByEmail(authenticationRequest.getEmail(), Collections.singleton(Profile.class))
                .orElseThrow(() -> new UsernameNotFoundException("No user found " + authenticationRequest.getEmail()));

        final UserDetails userDetails = ((UserDetailsServiceImpl)userDetailsService).convertUserToUserDetails(user);

        return new AuthenticationResponse(jwtUtil.generateToken(userDetails),
                                            new UserDTO(user));
    }

    public Optional<User> getCurrentUser(){
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByEmail(principal.getUsername()).orElse(null);

        return Optional.of(user);
    }
}
