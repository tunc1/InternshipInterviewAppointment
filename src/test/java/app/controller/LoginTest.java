package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import app.response.MessageResponse;
import app.response.TokenResponse;
import app.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginTest
{
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    TokenService tokenService;
    @Mock
    UserRepository userRepository;
    User user;
    Login login;

    @BeforeEach
    void init()
    {
        login=new Login(authenticationManager,tokenService,userRepository);
        user=new User();
        user.setUsername("username");
        user.setPassword("password");
    }
    @Test
    void login_successful()
    {
        String token="jwt.token";
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenAnswer(i->i.getArgument(0,Authentication.class));
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        Mockito.when(tokenService.create(user)).thenReturn(token);
        ResponseEntity<Object> responseEntity=login.login(user);
        Assertions.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
        Assertions.assertEquals(((TokenResponse)responseEntity.getBody()).getToken(),token);
    }
    @Test
    void login_throwsException()
    {
        String exceptionMessage="Exception";
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(new BadCredentialsException(exceptionMessage));
        ResponseEntity<Object> responseEntity=login.login(user);
        Assertions.assertEquals(responseEntity.getStatusCode(),HttpStatus.UNAUTHORIZED);
        Assertions.assertEquals(((MessageResponse)responseEntity.getBody()).getMessage(),exceptionMessage);
    }
}