package app.controller;

import app.entity.IUser;
import app.entity.User;
import app.repository.UserRepository;
import app.request.UpdatePasswordRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updatePassword")
public class UpdatePassword
{
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    public UpdatePassword(PasswordEncoder passwordEncoder,UserRepository userRepository)
    {
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
    }
    @PostMapping
    public boolean updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,Authentication authentication)
    {
        User user=((IUser)authentication.getPrincipal()).getUser();
		if(passwordEncoder.matches(updatePasswordRequest.getPassword(),user.getPassword()))
		{
			user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
			userRepository.save(user);
			return true;
		}
        return false;
    }
}