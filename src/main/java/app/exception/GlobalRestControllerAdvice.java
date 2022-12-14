package app.exception;

import app.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalRestControllerAdvice
{
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(value=HttpStatus.FORBIDDEN)
    public MessageResponse handleUnauthorizedException(UnauthorizedException exception)
    {
        return new MessageResponse(exception.getMessage());
    }
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public MessageResponse handleEntityNotFoundException(EntityNotFoundException exception)
    {
        return new MessageResponse("No Entity Found");
    }
}