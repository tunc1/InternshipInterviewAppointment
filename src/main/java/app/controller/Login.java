package app.controller;

import app.entity.User;
import app.repository.UserRepository;
import app.response.MessageResponse;
import app.response.TokenResponse;
import app.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class Login
{
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public ResponseEntity login(@RequestBody User user)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        try
        {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            User userDetails=userRepository.findByUsername(user.getUsername());
            String token=tokenService.create(userDetails);
            return new ResponseEntity<>(new TokenResponse(token),HttpStatus.OK);
        }
        catch(AuthenticationException e)
        {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
}
