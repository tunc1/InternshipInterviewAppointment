package app.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenService
{
    private String key="key";
    public String create(UserDetails userDetails)
    {
        long DAY=60*60*24*1000;
        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+DAY*30))
                .sign(Algorithm.HMAC512(key));
    }
    public boolean validate(String s)
    {
        try
        {
            JWT.require(Algorithm.HMAC512(key)).build().verify(s);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public String getUsername(String s)
    {
        return JWT.decode(s).getClaim("username").asString();
    }
}