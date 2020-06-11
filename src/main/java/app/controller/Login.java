package app.controller;

import app.entity.Student;
import app.entity.Teacher;
import app.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private UserDetailsService userDetailsService;
    @PostMapping("/teacher")
    public String teacher(@RequestBody Teacher user)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
        return tokenService.create(userDetails);
    }
    @PostMapping("/student")
    public String student(@RequestBody Student user)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUsername());
        return tokenService.create(userDetails);
    }
}