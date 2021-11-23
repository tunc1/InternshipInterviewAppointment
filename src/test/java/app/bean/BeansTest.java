package app.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class BeansTest
{
    @Test
    void passwordEncoder()
    {
        Beans beans=new Beans();
        Assertions.assertEquals(beans.passwordEncoder().getClass(),BCryptPasswordEncoder.class);
    }
}