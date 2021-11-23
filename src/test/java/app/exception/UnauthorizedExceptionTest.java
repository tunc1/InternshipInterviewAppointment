package app.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnauthorizedExceptionTest
{
    @Test
    void constructor()
    {
        UnauthorizedException unauthorizedException=new UnauthorizedException();
        Assertions.assertTrue(unauthorizedException.getMessage().equals("You do not have permission for this operation"));
    }
}