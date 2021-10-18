package app.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil
{
    private PasswordEncoder passwordEncoder;
    public PasswordUtil(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder=passwordEncoder;
    }
    public String encode(String plainPassword)
    {
        return passwordEncoder.encode(plainPassword);
    }
    public boolean matches(String plainPassword,String password)
    {
        return passwordEncoder.matches(plainPassword,password);
    }
}