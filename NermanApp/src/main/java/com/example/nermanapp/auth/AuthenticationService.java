package com.example.nermanapp.auth;

import com.example.nermanapp.config.JwtService;
import com.example.nermanapp.dto.Request.UserRequest.CreateUserRequest;
import com.example.nermanapp.enums.Role;
import com.example.nermanapp.enums.TokenType;
import com.example.nermanapp.model.Token;
import com.example.nermanapp.model.User;
import com.example.nermanapp.repository.TokeRepo;
import com.example.nermanapp.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokeRepo tokeRepo;

    public AuthenticationResponse createUser(CreateUserRequest request) {
        String email = request.getEmail();
        if(!isValidEmail(email)){
            return AuthenticationResponse.builder()
                    .status("Invalid email format.")
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
        }
        if (!isValidPassword(request.getPassword())) {
            return AuthenticationResponse.builder()
                    .status("The password must be at least 6 characters long and should not contain any special characters.")
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
        }
        var userCheck = userRepo.findByEmail(email).orElse(null);
        if(userCheck != null){
            return AuthenticationResponse.builder()
                    .status("Email is exist")
                    .accessToken(null)
                    .refreshToken(null)
                    .build();
        }
        var user = User.builder()
                .email(request.getEmail())
                .accountName(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .userStatus(true)
                .role(Role.CUSTOMER)
                .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                .accountBalance(0)
                .build();
        var save = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(save, jwtToken);
        return AuthenticationResponse.builder()
                .status("You have successfully registered.")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .userInfo(user)
                .build();
    }

    private boolean isValidPassword(String password) {
        //Check validate password
        return password != null && password.length() >= 6 && !password.matches(".*[^a-zA-Z0-9].*");
    }
    private boolean isValidEmail(String email) {
        // check valid email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .accountName(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .userStatus(request.isStatus())
                .role(request.getRole())
                .avatar(request.getAvatar())
                .build();
        var save = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(save, jwtToken);
        return AuthenticationResponse.builder()
                .status("You have successfully registered.")
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validToken = tokeRepo.findAllValidTokensByUser(user.getUsersID());
        if (validToken.isEmpty())
            return;
        validToken.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        tokeRepo.saveAll(validToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokeRepo.save(token);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        if (!user.isUserStatus()) {
            return AuthenticationResponse.builder()
                    .status("User is ban")
                    .build();
        }
        if(user == null || (user != null && !passwordEncoder.matches(request.getPassword(), user.getPassword()))){
            System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()));
            return AuthenticationResponse.builder()
                    .status("Wrong email or password")
                    .build();
        }
        else {
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .status("Login successfully")
//                .userInfo(userRepo.findUserByEmail(request.getEmail()).orElseThrow())
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .userInfo(user)
                    .build();
        }

    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);// todo extract the userEmail from JWT Token
        if (userEmail != null) {
            var user = this.userRepo.findUserByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
