package app.controller;

import app.entity.IUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsControllerTest
{
    @Test
    void get()
    {
        IUser user=Mockito.mock(IUser.class);
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        UserDetailsController userDetailsController=new UserDetailsController();
        Object actual=userDetailsController.get(authentication);
        Assertions.assertEquals(user,actual);
    }
}