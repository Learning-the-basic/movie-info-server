package com.movieinfo.sharewatch.web;

import com.movieinfo.sharewatch.config.auth.TokenProvider;
import com.movieinfo.sharewatch.domain.user.AuthProvider;
import com.movieinfo.sharewatch.domain.user.User;
import com.movieinfo.sharewatch.domain.user.UserRepository;
import com.movieinfo.sharewatch.exception.BadRequestException;
import com.movieinfo.sharewatch.web.dto.payload.ApiResponse;
import com.movieinfo.sharewatch.web.dto.payload.AuthResponse;
import com.movieinfo.sharewatch.web.dto.payload.LoginRequest;
import com.movieinfo.sharewatch.web.dto.payload.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = User.builder().name(signUpRequest.getName())
                                    .email(signUpRequest.getEmail())
                                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                                    .provider(AuthProvider.local).build();

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}