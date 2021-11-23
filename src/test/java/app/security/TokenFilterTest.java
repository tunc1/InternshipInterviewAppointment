package app.security;

import app.consts.Role;
import app.entity.Student;
import app.entity.Teacher;
import app.entity.User;
import app.repository.UserRepository;
import app.service.StudentService;
import app.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TokenFilterTest
{
    @Mock
    TokenService tokenService;
    @Mock
    TeacherService teacherService;
    @Mock
    StudentService studentService;
    @Mock
    UserRepository userRepository;
    @Mock
    HttpServletRequest request;
    TokenFilter tokenFilter;

    @BeforeEach
    void setUp()
    {
        tokenFilter=new TokenFilter(tokenService,teacherService,studentService,userRepository);
    }
    @Test
    void doFilterInternal_noAuthorizationHeader() throws ServletException, IOException
    {
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn(null);
        Assertions.assertDoesNotThrow(()->tokenFilter.doFilterInternal(request,null,null));
    }
    @Test
    void doFilterInternal_noBearer() throws ServletException,IOException
    {
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn("Token");
        Assertions.assertDoesNotThrow(()->tokenFilter.doFilterInternal(request,null,null));
    }
    @Test
    void doFilterInternal_notValid() throws ServletException,IOException
    {
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn("Bearer Token");
        Mockito.when(tokenService.validate(Mockito.anyString())).thenReturn(false);
        tokenFilter.doFilterInternal(request,null,null);
        Mockito.verify(tokenService,Mockito.times(0)).get(Mockito.anyString(),Mockito.eq("username"));
    }
    @Test
    void doFilterInternal_userNotExists() throws ServletException,IOException
    {
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn("Bearer Token");
        Mockito.when(tokenService.validate(Mockito.anyString())).thenReturn(true);
        Mockito.when(tokenService.get(Mockito.anyString(),Mockito.eq("username"))).thenReturn("username");
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        tokenFilter.doFilterInternal(request,null,null);
        Mockito.verify(tokenService,Mockito.times(0)).get(Mockito.anyString(),Mockito.eq("role"));
    }
    @Test
    void doFilterInternal_studentExists() throws ServletException,IOException
    {
        Student student=new Student();
        User user=new User();
        user.setRole(Role.STUDENT);
        student.setUser(user);
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn("Bearer Token");
        Mockito.when(tokenService.validate(Mockito.anyString())).thenReturn(true);
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
        Mockito.when(tokenService.get(Mockito.anyString(),Mockito.eq("username"))).thenReturn("username");
        Mockito.when(tokenService.get(Mockito.anyString(),Mockito.eq("role"))).thenReturn(Role.STUDENT);
        Mockito.when(studentService.findByUserUsername(Mockito.anyString())).thenReturn(student);
        tokenFilter.doFilterInternal(request,null,null);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertEquals(authentication.getPrincipal(),student);
        Assertions.assertEquals(authentication.getAuthorities(),student.getUser().getAuthorities());
    }
    @Test
    void doFilterInternal_teacherExists() throws ServletException,IOException
    {
        Teacher teacher=new Teacher();
        User user=new User();
        user.setRole(Role.TEACHER);
        teacher.setUser(user);
        Mockito.when(request.getHeader(Mockito.eq("Authorization"))).thenReturn("Bearer Token");
        Mockito.when(tokenService.validate(Mockito.anyString())).thenReturn(true);
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
        Mockito.when(tokenService.get(Mockito.anyString(),Mockito.eq("username"))).thenReturn("username");
        Mockito.when(tokenService.get(Mockito.anyString(),Mockito.eq("role"))).thenReturn(Role.TEACHER);
        Mockito.when(teacherService.findByUserUsername(Mockito.anyString())).thenReturn(teacher);
        tokenFilter.doFilterInternal(request,null,null);
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertEquals(authentication.getPrincipal(),teacher);
        Assertions.assertEquals(authentication.getAuthorities(),teacher.getUser().getAuthorities());
    }
}