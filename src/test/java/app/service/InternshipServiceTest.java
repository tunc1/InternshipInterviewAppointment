package app.service;

import app.entity.IUser;
import app.entity.Internship;
import app.entity.Student;
import app.entity.Teacher;
import app.exception.UnauthorizedException;
import app.repository.InternshipRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InternshipServiceTest
{
    @Mock
    InternshipRepository internshipRepository;
    InternshipService internshipService;

    @BeforeEach
    void setUp()
    {
        internshipService=new InternshipService(internshipRepository);
    }
    @Test
    void save()
    {
        Internship internship=new Internship();
        Mockito.when(internshipRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Internship.class));
        Internship saved=internshipService.save(internship);
        Assertions.assertEquals(saved,internship);
    }
    @Test
    void update()
    {
        Internship internship=new Internship();
        Mockito.when(internshipRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Internship.class));
        Internship updated=internshipService.update(internship);
        Assertions.assertEquals(updated,internship);
    }
    @Test
    void deleteById()
    {
        internshipService.deleteById(1);
        Mockito.verify(internshipRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsInternship_teacher()
    {
        IUser user=new Teacher();
        Internship internship=new Internship();
        Mockito.when(internshipRepository.findById(Mockito.any())).thenReturn(Optional.of(internship));
        Internship actual=internshipService.findById(1,user);
        Assertions.assertEquals(actual,internship);
    }
    @Test
    void findById_returnsInternship_student()
    {
        Student user=new Student();
        Internship internship=new Internship();
        internship.setStudent(user);
        Mockito.when(internshipRepository.findById(Mockito.any())).thenReturn(Optional.of(internship));
        Internship actual=internshipService.findById(1,user);
        Assertions.assertEquals(actual,internship);
    }
    @Test
    void findById_throwsUnauthorizedException()
    {
        Student user=new Student();
        Student user2=new Student();
        Internship internship=new Internship();
        internship.setStudent(user2);
        Mockito.when(internshipRepository.findById(Mockito.any())).thenReturn(Optional.of(internship));
        Internship actual=internshipService.findById(1,user);
        Assertions.assertEquals(actual,internship);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        IUser user=Mockito.mock(IUser.class);
        Mockito.when(internshipRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->internshipService.findById(1,user));
    }
    @Test
    void findAll_teacher()
    {
        IUser user=new Teacher();
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipRepository.findAll()).thenReturn(internships);
        List<Internship> actual=internshipService.findAll(user);
        Assertions.assertEquals(actual,internships);
    }
    @Test
    void findAll_student_error()
    {
        IUser user=new Student();
        Assertions.assertThrows(UnauthorizedException.class,()->internshipService.findAll(user));
    }
    @Test
    void findByStudentId_teacher_returns()
    {
        int studentId=1;
        IUser user=new Teacher();
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipRepository.findByStudentId(studentId)).thenReturn(internships);
        List<Internship> actual=internshipService.findByStudentId(studentId,user);
        Assertions.assertEquals(actual,internships);
    }
    @Test
    void findByStudentId_student_returns()
    {
        int studentId=1;
        Student user=new Student();
        user.setId(studentId);
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipRepository.findByStudentId(studentId)).thenReturn(internships);
        List<Internship> actual=internshipService.findByStudentId(studentId,user);
        Assertions.assertEquals(actual,internships);
    }
    @Test
    void findByStudentId_student_throwsUnauthorizedException()
    {
        int studentId=1;
        Student user=new Student();
        user.setId(2);
        List<Internship> internships=List.of(new Internship());
        Mockito.when(internshipRepository.findByStudentId(studentId)).thenReturn(internships);
        Assertions.assertThrows(UnauthorizedException.class,()->internshipService.findByStudentId(studentId,user));
    }
}