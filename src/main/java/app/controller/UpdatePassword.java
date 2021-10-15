package app.controller;

import app.entity.Teacher;
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
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @PostMapping("/teacher")
    public boolean teacher(@RequestBody UpdatePasswordRequest updatePasswordRequest,Authentication authentication)
    {
		Teacher teacher=(Teacher)authentication.getPrincipal();
		if(passwordEncoder.matches(updatePasswordRequest.getPassword(),teacher.getPassword()))
		{
			teacher.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
			teacherService.update(teacher);
			return true;
		}
        return false;
    }
	@PostMapping("/student")
    public boolean student(@RequestBody UpdatePasswordRequest updatePasswordRequest,Authentication authentication)
    {
        Student student=(Student)authentication.getPrincipal();
		if(passwordEncoder.matches(updatePasswordRequest.getPassword(),student.getPassword()))
		{
			student.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
			studentService.update(student);
			return true;
		}
        return false;
    }
}