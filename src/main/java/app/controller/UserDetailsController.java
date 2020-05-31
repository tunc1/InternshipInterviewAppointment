package app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController
{
    @PostMapping
    public UserDetails get(Authentication authentication)
    {
        return (UserDetails)authentication.getPrincipal();
    }
}