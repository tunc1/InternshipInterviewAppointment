package app.controller;

import app.entity.Teacher;
import app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController
{
    @Autowired
    private TeacherService teacherService;
    @PostMapping
    public Teacher save(@RequestBody Teacher teacher)
    {
        teacherService.save(teacher);
        return teacher;
    }
    @PutMapping
    public void update(@RequestBody Teacher teacher)
    {
        teacherService.update(teacher);
    }
    @GetMapping("/{id}")
    public Teacher findById(@PathVariable int id)
    {
        return teacherService.findById(id);
    }
    @GetMapping
    public List<Teacher> findAll()
    {
        return teacherService.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        teacherService.deleteById(id);
    }
}