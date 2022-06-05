package app.service;

import app.consts.Role;
import app.entity.Student;
import app.entity.User;
import app.repository.StudentRepository;
import app.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentService
{
    private StudentRepository studentRepository;
    private PasswordUtil passwordUtil;
    public StudentService(StudentRepository studentRepository,PasswordUtil passwordUtil)
    {
        this.studentRepository=studentRepository;
        this.passwordUtil=passwordUtil;
    }
    public Student save(Student student)
    {
        User user=new User();
        user.setRole(Role.STUDENT);
        user.setUsername(student.getNumber());
        user.setPassword(passwordUtil.encode(student.getNumber()));
        student.setUser(user);
        return studentRepository.save(student);
    }
    public Student update(Student student)
    {
        return studentRepository.save(student);
    }
    public Student findById(int id)
    {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<Student> findAll()
    {
        return studentRepository.findAll();
    }
    public void deleteById(int id)
    {
        studentRepository.deleteById(id);
    }
    public Student findByUserUsername(String username)
    {
        return studentRepository.findByUserUsername(username);
    }
}