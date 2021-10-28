package app.controller;

import app.response.UserTypeResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userType")
public class UserType
{
    @GetMapping
    public UserTypeResponse userType(Authentication authentication)
    {
        return new UserTypeResponse(authentication.getPrincipal().getClass().getSimpleName());
    }
}