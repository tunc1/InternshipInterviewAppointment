package app.service;

import app.consts.Role;
import app.entity.Teacher;
import app.repository.TeacherRepository;
import app.util.PasswordUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest
{
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    PasswordUtil passwordUtil;
    @Captor
    ArgumentCaptor<Teacher> teacherArgumentCaptor;
    TeacherService teacherService;

    @BeforeEach
    void setUp()
    {
        teacherService=new TeacherService(teacherRepository,passwordUtil);
    }
    @Test
    void save()
    {
        Teacher teacher=new Teacher();
        Mockito.when(teacherRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Teacher.class));
        Teacher saved=teacherService.save(teacher);
        Assertions.assertEquals(saved,teacher);
    }
    @Test
    void update()
    {
        Teacher teacher=new Teacher();
        Mockito.when(teacherRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Teacher.class));
        Teacher updated=teacherService.update(teacher);
        Assertions.assertEquals(updated,teacher);
    }
    @Test
    void deleteById()
    {
        teacherService.deleteById(1);
        Mockito.verify(teacherRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsTeacher()
    {
        Teacher teacher=new Teacher();
        Mockito.when(teacherRepository.findById(Mockito.any())).thenReturn(Optional.of(teacher));
        Teacher actual=teacherService.findById(1);
        Assertions.assertEquals(actual,teacher);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        Mockito.when(teacherRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->teacherService.findById(1));
    }
    @Test
    void findAll()
    {
        List<Teacher> teachers=List.of(new Teacher());
        Mockito.when(teacherRepository.findAll()).thenReturn(teachers);
        List<Teacher> actual=teacherService.findAll();
        Assertions.assertEquals(actual,teachers);
    }
    @Test
    void addNewTeacher_adds() throws IllegalAccessException
    {
        String username="username",password="password";
        Field adminUsernameField=TeacherService.class.getDeclaredFields()[0];
        Field adminPasswordField=TeacherService.class.getDeclaredFields()[1];
        adminUsernameField.setAccessible(true);
        adminPasswordField.setAccessible(true);
        adminUsernameField.set(teacherService,username);
        adminPasswordField.set(teacherService,password);
        Mockito.when(passwordUtil.encode(Mockito.anyString())).thenReturn("encoded_password");
        Mockito.when(teacherRepository.count()).thenReturn(0L);
        teacherService.addNewTeacher();
        Mockito.verify(teacherRepository).save(teacherArgumentCaptor.capture());
        Teacher teacher=teacherArgumentCaptor.getValue();
        Assertions.assertTrue(teacher.getUser().isAccountNonExpired());
        Assertions.assertTrue(teacher.getUser().isAccountNonLocked());
        Assertions.assertTrue(teacher.getUser().isCredentialsNonExpired());
        Assertions.assertTrue(teacher.getUser().isEnabled());
        Assertions.assertTrue(teacher.getUser().getRole().equals(Role.TEACHER));
        Assertions.assertTrue(teacher.getUser().getUsername().equals(username));
        Assertions.assertFalse(teacher.getUser().getPassword().equals(password));
    }
    @Test
    void addNewTeacher_notAdds()
    {
        Mockito.when(teacherRepository.count()).thenReturn(1L);
        teacherService.addNewTeacher();
        Mockito.verify(teacherRepository,Mockito.times(0)).save(Mockito.any());
    }
    @Test
    void findByUserUsername()
    {
        String username="username";
        Teacher teacher=new Teacher();
        Mockito.when(teacherRepository.findByUserUsername(Mockito.any())).thenReturn(teacher);
        Teacher actual=teacherService.findByUserUsername(username);
        Assertions.assertEquals(actual,teacher);
    }
}