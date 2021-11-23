package app.controller;

import app.entity.IUser;
import app.entity.Internship;
import app.entity.Student;
import app.entity.User;
import app.service.InternshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class InternshipControllerTest
{
    @Mock
    InternshipService internshipService;
    InternshipController internshipController;

    @BeforeEach
    void setUp()
    {
        internshipController=new InternshipController(internshipService);
    }
    @Test
    void save()
    {
        Student student=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(student,null);
        Internship internship=new Internship();
        Mockito.when(internshipService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Internship.class));
        Internship saved=internshipController.save(internship,authentication);
        Assertions.assertEquals(saved,internship);
        Assertions.assertEquals(student,saved.getStudent());
    }
    @Test
    void update()
    {
        Integer id=1;
        Internship internship=new Internship();
        Mockito.when(internshipService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Internship.class));
        Internship updated=internshipController.update(internship,id);
        Assertions.assertEquals(updated,internship);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        internshipController.deleteById(1);
        Mockito.verify(internshipService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsInternship()
    {
        Student student=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(student,null);
        Internship internship=new Internship();
        Mockito.when(internshipService.findById(Mockito.anyInt(),Mockito.any())).thenReturn(internship);
        Internship actual=internshipController.findById(1,authentication);
        Assertions.assertEquals(actual,internship);
    }
    @Test
    void findAll()
    {
        Student student=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(student,null);
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipService.findAll(Mockito.any())).thenReturn(internships);
        List<Internship> actual=internshipController.findAll(authentication);
        Assertions.assertEquals(actual,internships);
    }
    @Test
    void findByStudentId()
    {
        IUser user=User::new;
        int studentId=1;
        Authentication authentication=new UsernamePasswordAuthenticationToken(user,null);
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipService.findByStudentId(Mockito.anyInt(),Mockito.any())).thenReturn(internships);
        List<Internship> actual=internshipController.findByStudentId(studentId,authentication);
        Assertions.assertEquals(actual,internships);
    }
}