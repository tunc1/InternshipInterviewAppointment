package app.controller;

import app.entity.Internship;
import app.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
public class InternshipController
{
    @Autowired
    private InternshipService internshipService;
    @PostMapping
    public Internship save(@RequestBody Internship internship)
    {
        internshipService.save(internship);
        return internship;
    }
    @PutMapping
    public void update(@RequestBody Internship internship)
    {
        internshipService.update(internship);
    }
    @GetMapping("/{id}")
    public Internship findById(@PathVariable int id)
    {
        return internshipService.findById(id);
    }
    @GetMapping
    public List<Internship> findAll()
    {
        return internshipService.findAll();
    }
    @GetMapping("/student/{studentId}")
    public List<Internship> findByStudentId(@PathVariable int studentId)
    {
        return internshipService.findByStudentId(studentId);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        internshipService.deleteById(id);
    }
}