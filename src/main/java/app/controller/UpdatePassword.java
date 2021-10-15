package app.controller;

import app.entity.IUser;
import app.entity.Teacher;
import app.entity.User;
import app.repository.UserRepository;
import app.request.UpdatePasswordRequest;
import app.service.TeacherService;
import app.entity.Student;
import app.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updatePassword")
public class UpdatePassword
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/teacher")
    public boolean teacher(@RequestBody UpdatePasswordRequest updatePasswordRequest,Authentication authentication)
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