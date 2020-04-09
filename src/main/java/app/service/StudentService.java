package app.service;

import app.entity.Student;
import app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentRepository studentRepository;
    public void save(Student student)
    {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
    }
    public void update(Student student)
    {
        studentRepository.save(student);
    }
    public Student findById(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }
    public List<Student> findAll()
    {
        return studentRepository.findAll();
    }
    public void deleteById(int id)
    {
        studentRepository.deleteById(id);
    }
    public UserDetails findByUsername(String username)
    {
        return studentRepository.findByUsername(username);
    }
}