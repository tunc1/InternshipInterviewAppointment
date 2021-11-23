package app.controller;

import app.entity.Student;
import app.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest
{
    @Mock
    StudentService studentService;
    StudentController studentController;

    @BeforeEach
    void setUp()
    {
        studentController=new StudentController(studentService);
    }
    @Test
    void save()
    {
        Student student=new Student();
        Mockito.when(studentService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Student.class));
        Student saved=studentController.save(student);
        Assertions.assertEquals(saved,student);
    }
    @Test
    void update()
    {
        Integer id=1;
        Student student=new Student();
        Mockito.when(studentService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Student.class));
        Student updated=studentController.update(student,id);
        Assertions.assertEquals(updated,student);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        studentController.deleteById(1);
        Mockito.verify(studentService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsStudent()
    {
        Student student=new Student();
        Mockito.when(studentService.findById(Mockito.anyInt())).thenReturn(student);
        Student actual=studentController.findById(1);
        Assertions.assertEquals(actual,student);
    }
    @Test
    void findAll()
    {
        List<Student> students=List.of(new Student());
        Mockito.when(studentService.findAll()).thenReturn(students);
        List<Student> actual=studentController.findAll();
        Assertions.assertEquals(actual,students);
    }
}