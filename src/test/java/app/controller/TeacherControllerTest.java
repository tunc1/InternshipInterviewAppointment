package app.controller;

import app.entity.Teacher;
import app.service.TeacherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest
{
    @Mock
    TeacherService teacherService;
    TeacherController teacherController;

    @BeforeEach
    void setUp()
    {
        teacherController=new TeacherController(teacherService);
    }
    @Test
    void save()
    {
        Teacher teacher=new Teacher();
        Mockito.when(teacherService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Teacher.class));
        Teacher saved=teacherController.save(teacher);
        Assertions.assertEquals(saved,teacher);
    }
    @Test
    void update()
    {
        Integer id=1;
        Teacher teacher=new Teacher();
        Mockito.when(teacherService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Teacher.class));
        Teacher updated=teacherController.update(teacher,id);
        Assertions.assertEquals(updated,teacher);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        teacherController.deleteById(1);
        Mockito.verify(teacherService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsTeacher()
    {
        Teacher teacher=new Teacher();
        Mockito.when(teacherService.findById(Mockito.anyInt())).thenReturn(teacher);
        Teacher actual=teacherController.findById(1);
        Assertions.assertEquals(actual,teacher);
    }
    @Test
    void findAll()
    {
        List<Teacher> teachers=List.of(new Teacher());
        Mockito.when(teacherService.findAll()).thenReturn(teachers);
        List<Teacher> actual=teacherController.findAll();
        Assertions.assertEquals(actual,teachers);
    }
}