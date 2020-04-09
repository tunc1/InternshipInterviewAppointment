package app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class User
{
    @PostMapping
    public UserDetails userDetails()
    {
        return (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}