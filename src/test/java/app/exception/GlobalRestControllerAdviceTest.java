package app.exception;

import app.response.MessageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalRestControllerAdviceTest
{
    GlobalRestControllerAdvice globalRestControllerAdvice;
    @BeforeEach
    void setUp()
    {
        globalRestControllerAdvice=new GlobalRestControllerAdvice();
    }
    @Test
    void handleUnauthorizedException()
    {
        UnauthorizedException unauthorizedException=new UnauthorizedException();
        MessageResponse messageResponse=globalRestControllerAdvice.handleUnauthorizedException(unauthorizedException);
        Assertions.assertTrue(unauthorizedException.getMessage().equals(messageResponse.getMessage()));
    }
    @Test
    void handleEntityNotFoundException()
    {
        EntityNotFoundException entityNotFoundException=new EntityNotFoundException();
        MessageResponse messageResponse=globalRestControllerAdvice.handleEntityNotFoundException(entityNotFoundException);
        Assertions.assertTrue(messageResponse.getMessage().equals("No Entity Found"));
    }
}