package com.kulturman.mdd.controllers;

import com.kulturman.mdd.dtos.requests.LoginRequest;
import com.kulturman.mdd.dtos.requests.RegisterRequest;
import com.kulturman.mdd.dtos.responses.LoginResponse;
import com.kulturman.mdd.dtos.responses.auth.me.GetUserProfile;
import com.kulturman.mdd.services.JwtService;
import com.kulturman.mdd.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest)  {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        ));
        return ResponseEntity.ok(new LoginResponse(jwtService.generateToken(authentication)));
    }

    @GetMapping("me")
    public ResponseEntity<GetUserProfile> me() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

}
