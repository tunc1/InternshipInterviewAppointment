package app.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class TokenCheck
{
    @PostMapping
    public Response userDetails(Authentication authentication)
    {
        return new Response(authentication.getPrincipal().getClass().getSimpleName());
    }
    @Getter
    @Setter
    @AllArgsConstructor
    private static class Response
    {
        private String userType;
    }
}