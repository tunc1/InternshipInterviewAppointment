package app.controller;

import app.entity.Teacher;
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
    public boolean teacher(@RequestBody PasswordHolder passwordHolder,Authentication authentication)
    {
		Teacher teacher=(Teacher)authentication.getPrincipal();
		if(passwordEncoder.matches(passwordHolder.getPassword(),teacher.getPassword()))
		{
			teacher.setPassword(passwordEncoder.encode(passwordHolder.getNewPassword()));
			teacherService.update(teacher);
			return true;
		}
        return false;
    }
	@PostMapping("/student")
    public boolean student(@RequestBody PasswordHolder passwordHolder,Authentication authentication)
    {
        Student student=(Student)authentication.getPrincipal();
		if(passwordEncoder.matches(passwordHolder.getPassword(),student.getPassword()))
		{
			student.setPassword(passwordEncoder.encode(passwordHolder.getNewPassword()));
			studentService.update(student);
			return true;
		}
        return false;
    }
	private static class PasswordHolder
    {
		private String password,newPassword;
		public String getPassword()
		{
			return password;
		}
		public void setPassword(String password)
		{
			this.password=password;
		}
		public String getNewPassword()
		{
			return newPassword;
		}
		public void setNewPassword(String newPassword)
		{
			this.newPassword=newPassword;
		}
	}
}