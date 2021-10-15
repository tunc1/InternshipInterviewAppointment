package app.controller;

import app.entity.Student;
import app.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController
{
    private StudentService studentService;
    public StudentController(StudentService studentService)
    {
        this.studentService=studentService;
    }
    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Student save(@RequestBody Student student)
    {
        return studentService.save(student);
    }
    @PutMapping("/{id}")
    public Student update(@RequestBody Student student,@PathVariable Integer id)
    {
        student.setId(id);
        return studentService.update(student);
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
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        studentService.deleteById(id);
    }
}