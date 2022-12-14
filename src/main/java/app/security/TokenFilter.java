package app.security;

import app.consts.Role;
import app.entity.Student;
import app.entity.Teacher;
import app.repository.UserRepository;
import app.service.StudentService;
import app.service.TeacherService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter
{
    private TokenService tokenService;
    private TeacherService teacherService;
    private StudentService studentService;
    private UserRepository userRepository;
    public TokenFilter(TokenService tokenService,TeacherService teacherService,StudentService studentService,UserRepository userRepository)
    {
        this.tokenService=tokenService;
        this.teacherService=teacherService;
        this.studentService=studentService;
        this.userRepository=userRepository;
    }
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException
    {
        String authorization=request.getHeader("Authorization");
        if(authorization!=null)
        {
            if(authorization.contains("Bearer "))
            {
                String token=authorization.split("Bearer ")[1];
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
            }
        }
        if(chain!=null)
            chain.doFilter(request,response);
    }
}