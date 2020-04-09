package app.security;

import app.service.StudentService;
import app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails userDetails=teacherService.findByUsername(username);
        if(userDetails==null)
            userDetails=studentService.findByUsername(username);
        return userDetails;
    }
}