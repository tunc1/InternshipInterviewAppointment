package app.service;

import app.consts.Role;
import app.entity.Student;
import app.repository.StudentRepository;
import app.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest
{
    @Mock
    StudentRepository studentRepository;
    StudentService studentService;
    @Mock
    PasswordUtil passwordUtil;

    @BeforeEach
    void setUp()
    {
        studentService=new StudentService(studentRepository,passwordUtil);
    }
    @Test
    void save()
    {
        Student student=new Student();
        student.setNumber("1234");
        Mockito.when(studentRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Student.class));
        Mockito.when(passwordUtil.encode(Mockito.anyString())).thenReturn("encoded_password");
        Student saved=studentService.save(student);
        Assertions.assertEquals(saved,student);
        Assertions.assertEquals(student.getNumber(),saved.getUser().getUsername());
        Assertions.assertEquals(Role.STUDENT,saved.getUser().getRole());
        Assertions.assertEquals("encoded_password",saved.getUser().getPassword());
    }
    @Test
    void update()
    {
        Student student=new Student();
        Mockito.when(studentRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Student.class));
        Student updated=studentService.update(student);
        Assertions.assertEquals(updated,student);
    }
    @Test
    void deleteById()
    {
        studentService.deleteById(1);
        Mockito.verify(studentRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsStudent()
    {
        Student student=new Student();
        Mockito.when(studentRepository.findById(Mockito.any())).thenReturn(Optional.of(student));
        Student actual=studentService.findById(1);
        Assertions.assertEquals(actual,student);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        Mockito.when(studentRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->studentService.findById(1));
    }
    @Test
    void findAll()
    {
        List<Student> students=List.of(new Student());
        Mockito.when(studentRepository.findAll()).thenReturn(students);
        List<Student> actual=studentService.findAll();
        Assertions.assertEquals(actual,students);
    }
    @Test
    void findByUserUsername()
    {
        String username="username";
        Student student=new Student();
        Mockito.when(studentRepository.findByUserUsername(Mockito.any())).thenReturn(student);
        Student actual=studentService.findByUserUsername(username);
        Assertions.assertEquals(actual,student);
    }
}