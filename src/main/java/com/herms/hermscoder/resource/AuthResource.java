package com.herms.hermscoder.resource;

import com.herms.hermscoder.model.AuthenticationResponse;
import com.herms.hermscoder.model.dto.AuthenticationRequest;
import com.herms.hermscoder.model.dto.UserRegistration;
import com.herms.hermscoder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid UserRegistration userRegistration) {
        authService.signUp(userRegistration);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authService.login(authenticationRequest));
    }

    @GetMapping("/token_refresh")
    public ResponseEntity<AuthenticationResponse> login() {
        return ResponseEntity.ok(authService.refreshToken());
    }
}
