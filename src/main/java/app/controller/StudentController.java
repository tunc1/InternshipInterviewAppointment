package app.controller;

import app.entity.Student;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    @Autowired
    private StudentService studentService;
    @PostMapping
    public Student save(@RequestBody Student student)
    {
        studentService.save(student);
        return student;
    }
    @PutMapping
    public void update(@RequestBody Student student)
    {
        studentService.update(student);
    }
    @GetMapping("/{id}")
    public Student findById(@PathVariable int id)
    {
        return studentService.findById(id);
    }
    @GetMapping
    public List<Student> findAll()
    {
        return studentService.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        studentService.deleteById(id);
    }
}