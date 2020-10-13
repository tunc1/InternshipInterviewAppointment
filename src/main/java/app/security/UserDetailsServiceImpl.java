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
    private String type;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails userDetails=null;
        if(type.equals("teacher"))
            userDetails=teacherService.findByUsername(username);
        else if(type.equals("student"))
            userDetails=studentService.findByUsername(username);
        if(userDetails==null)
            throw new UsernameNotFoundException(username);
        return userDetails;
    }
    public void setType(String type)
    {
        this.type=type;
    }
}