package app.service;

import app.entity.Teacher;
import app.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeacherService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TeacherRepository teacherRepository;
    public void save(Teacher teacher)
    {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
    }
    public void update(Teacher teacher)
    {
        teacherRepository.save(teacher);
    }
    public Teacher findById(int id)
    {
        return teacherRepository.findById(id).orElse(null);
    }
    public List<Teacher> findAll()
    {
        return teacherRepository.findAll();
    }
    public void deleteById(int id)
    {
        teacherRepository.deleteById(id);
    }
    public Teacher findByUsername(String username)
    {
        return teacherRepository.findByUsername(username);
    }
    @PostConstruct
    public void addNewTeacher()
    {
        if(!teacherRepository.existsByUsername("admin"))
        {
            Teacher teacher=new Teacher();
            teacher.setUsername("admin");
            teacher.setPassword("password");
            save(teacher);
        }
    }
}