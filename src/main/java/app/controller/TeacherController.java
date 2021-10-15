package app.controller;

import app.entity.Teacher;
import app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController
{
    @Autowired
    private TeacherService teacherService;
    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public Teacher save(@RequestBody Teacher teacher)
    {
        return teacherService.save(teacher);
    }
    @PutMapping("/{id}")
    public Teacher update(@RequestBody Teacher teacher,@PathVariable Integer id)
    {
        teacher.setId(id);
        return teacherService.update(teacher);
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
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        teacherService.deleteById(id);
    }
}