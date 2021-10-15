package app.security;

import app.consts.Role;
import app.entity.Student;
import app.entity.Teacher;
import app.repository.UserRepository;
import app.service.StudentService;
import app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserRepository userRepository;
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException
    {
        String token=request.getHeader("token");
        if(token!=null)
        {
            if(tokenService.validate(token))
            {
                String username=tokenService.get(token,"username");
                if(userRepository.existsByUsername(username))
                {
                    String role=tokenService.get(token,"role");
                    if(role.equals(Role.TEACHER))
                    {
                        Teacher teacher=teacherService.findByUserUsername(username);
                        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(teacher,null,teacher.getUser().getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                    else if(role.equals(Role.STUDENT))
                    {
                        Student student=studentService.findByUserUsername(username);
                        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(student,null,student.getUser().getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        chain.doFilter(request,response);
    }
}