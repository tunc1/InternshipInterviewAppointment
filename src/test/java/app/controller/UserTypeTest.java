package app.controller;

import app.entity.IUser;
import app.entity.Student;
import app.response.UserTypeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class UserTypeTest
{
    UserType userType;
    @BeforeEach
    void setUp()
    {
        userType=new UserType();
    }
    @Test
    void userType_student()
    {
        IUser user=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        UserTypeResponse userTypeResponse=userType.userType(authentication);
        Assertions.assertTrue(user.getClass().getSimpleName().equals(userTypeResponse.getUserType()));
    }
    @Test
    void userType_teacher()
    {
        IUser user=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        UserTypeResponse userTypeResponse=userType.userType(authentication);
        Assertions.assertTrue(user.getClass().getSimpleName().equals(userTypeResponse.getUserType()));
    }
}