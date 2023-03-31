package com.tcam.auth.service;

import com.tcam.auth.domain.AuthenticationRequestDTO;
import com.tcam.auth.domain.AuthenticationResponse;
import com.tcam.auth.domain.SingUpDTO;
import com.tcam.auth.model.Role;
import com.tcam.auth.model.User;
import com.tcam.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(SingUpDTO singUpDTO) {
        var user = User.builder()
                .userName(singUpDTO.email())
                .name(singUpDTO.firstName())
                .lastName(singUpDTO.lastName())
                .password(passwordEncoder.encode(singUpDTO.password()))
                .email(singUpDTO.email())
                .role(Role.USER)
                .bio(singUpDTO.bio())
                .avatar(singUpDTO.avatar())
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        var jwtToken =  jwtService.generateToken(user);
        return AuthenticationResponse.builder().id(user.getId()).token(jwtToken).build();
    }

    public AuthenticationResponse login(AuthenticationRequestDTO authenticationRequestDTO) {
         authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authenticationRequestDTO.email(),
                authenticationRequestDTO.password()
        ));
         var user = userRepository.findByEmail(authenticationRequestDTO.email())
                 .orElseThrow();
        var jwtToken =  jwtService.generateToken(user);
        return AuthenticationResponse.builder().id(user.getId()).token(jwtToken).build();

    }
}

