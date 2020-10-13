package app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userType")
public class UserType
{
    @PostMapping
    public Response userType(Authentication authentication)
    {
        return new Response(authentication.getPrincipal().getClass().getSimpleName());
    }
    private static class Response
    {
        private String userType;
        public Response(String userType)
        {
            this.userType=userType;
        }
        public String getUserType()
        {
            return userType;
        }
        public void setUserType(String userType)
        {
            this.userType=userType;
        }
    }
}