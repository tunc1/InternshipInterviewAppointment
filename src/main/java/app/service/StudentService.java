package app.service;

import app.entity.Student;
import app.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository=studentRepository;
    }
    public Student save(Student student)
    {
        return studentRepository.save(student);
    }
    public Student update(Student student)
    {
        return studentRepository.save(student);
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
    public Student findByUserUsername(String username)
    {
        return studentRepository.findByUserUsername(username);
    }
}