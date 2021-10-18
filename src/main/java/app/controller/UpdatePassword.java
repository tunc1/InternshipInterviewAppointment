package app.controller;

import app.entity.IUser;
import app.entity.User;
import app.repository.UserRepository;
import app.request.UpdatePasswordRequest;
import app.util.PasswordUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updatePassword")
public class UpdatePassword
{
    private PasswordUtil passwordUtil;
    private UserRepository userRepository;
    public UpdatePassword(PasswordUtil passwordUtil,UserRepository userRepository)
    {
        this.passwordUtil=passwordUtil;
        this.userRepository=userRepository;
    }
    @PostMapping
    public boolean updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,Authentication authentication)
    {
        User user=((IUser)authentication.getPrincipal()).getUser();
		if(passwordUtil.matches(updatePasswordRequest.getPassword(),user.getPassword()))
		{
			user.setPassword(passwordUtil.encode(updatePasswordRequest.getNewPassword()));
			userRepository.save(user);
			return true;
		}
        return false;
    }
}