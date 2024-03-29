package app.service;

import app.consts.Role;
import app.entity.Teacher;
import app.entity.User;
import app.repository.TeacherRepository;
import app.util.PasswordUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeacherService
{
    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;
    private TeacherRepository teacherRepository;
    private PasswordUtil passwordUtil;
    public TeacherService(TeacherRepository teacherRepository,PasswordUtil passwordUtil)
    {
        this.teacherRepository=teacherRepository;
        this.passwordUtil=passwordUtil;
    }
    public Teacher save(Teacher teacher)
    {
        return teacherRepository.save(teacher);
    }
    public Teacher update(Teacher teacher)
    {
        return teacherRepository.save(teacher);
    }
    public Teacher findById(int id)
    {
        return teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Teacher> findAll()
    {
        return teacherRepository.findAll();
    }
    public void deleteById(int id)
    {
        teacherRepository.deleteById(id);
    }
    public Teacher findByUserUsername(String username)
    {
        return teacherRepository.findByUserUsername(username);
    }
    @PostConstruct
    public void addNewTeacher()
    {
        if(teacherRepository.count()==0)
        {
            Teacher teacher=new Teacher();
            User user=new User();
            user.setRole(Role.TEACHER);
            user.setUsername(username);
            user.setPassword(passwordUtil.encode(password));
            teacher.setUser(user);
            save(teacher);
        }
    }
}