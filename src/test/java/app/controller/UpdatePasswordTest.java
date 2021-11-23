package app.controller;

import app.entity.IUser;
import app.entity.Student;
import app.entity.User;
import app.repository.UserRepository;
import app.request.UpdatePasswordRequest;
import app.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class UpdatePasswordTest
{
    @Mock
    PasswordUtil passwordUtil;
    @Mock
    UserRepository userRepository;
    UpdatePassword updatePassword;
    @BeforeEach
    void setUp()
    {
        updatePassword=new UpdatePassword(passwordUtil,userRepository);
    }
    @Test
    void updatePassword_true()
    {
        String encodedNewPassword="encodedNewPassword",newPassword="newPassword";
        Mockito.when(passwordUtil.matches(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(passwordUtil.encode(Mockito.any())).thenReturn(encodedNewPassword);
        User user1=new User();
        user1.setPassword("password");
        IUser user=()->user1;
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        UpdatePasswordRequest updatePasswordRequest=new UpdatePasswordRequest();
        updatePasswordRequest.setPassword(user.getUser().getPassword());
        updatePasswordRequest.setNewPassword(newPassword);
        Assertions.assertTrue(updatePassword.updatePassword(updatePasswordRequest,authentication));
        Mockito.verify(userRepository).save(Mockito.any());
        Mockito.verify(passwordUtil).encode(Mockito.eq(newPassword));
        Assertions.assertTrue(user.getUser().getPassword().equals(encodedNewPassword));
    }
    @Test
    void updatePassword_false()
    {
        Mockito.when(passwordUtil.matches(Mockito.any(),Mockito.any())).thenReturn(false);
        IUser user=User::new;
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        UpdatePasswordRequest updatePasswordRequest=new UpdatePasswordRequest();
        updatePasswordRequest.setPassword("password");
        updatePasswordRequest.setNewPassword("newPassword");
        Assertions.assertFalse(updatePassword.updatePassword(updatePasswordRequest,authentication));
    }
}