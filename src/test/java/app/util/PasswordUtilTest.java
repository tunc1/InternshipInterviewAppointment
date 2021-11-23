package app.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordUtilTest
{
    @Mock
    PasswordEncoder passwordEncoder;
    PasswordUtil passwordUtil;
    @BeforeEach
    void setUp()
    {
        passwordUtil=new PasswordUtil(passwordEncoder);
    }
    @Test
    void encode()
    {
        passwordUtil.encode("plainPassword");
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
    }
    @Test
    void matches()
    {
        passwordUtil.matches("plainPassword","password");
        Mockito.verify(passwordEncoder).matches(Mockito.anyString(),Mockito.anyString());
    }
}