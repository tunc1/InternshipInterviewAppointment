package app.controller;

import app.entity.Internship;
import app.entity.Student;
import app.entity.Teacher;
import app.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
public class InternshipController
{
    @Autowired
    private InternshipService internshipService;
    @PostMapping
    public Internship save(@RequestBody Internship internship,Authentication authentication)
    {
        Student student=(Student)authentication.getPrincipal();
        internship.setStudent(student);
        internshipService.save(internship);
        return internship;
    }
    @PutMapping
    public void update(@RequestBody Internship internship)
    {
        internshipService.update(internship);
    }
    @GetMapping("/{id}")
    public Internship findById(@PathVariable int id,Authentication authentication)
    {
        Internship internship=internshipService.findById(id);
        if(authentication.getPrincipal() instanceof Teacher)
            return internship;
        else
        {
            Student student=(Student)authentication.getPrincipal();
            if(internship.getStudent().getId()==student.getId())
                return internship;
            return null;
        }
    }
    @GetMapping
    public List<Internship> findAll(Authentication authentication)
    {
        if(authentication.getPrincipal() instanceof Teacher)
            return internshipService.findAll();
        else
            return List.of();
    }
    @GetMapping("/student/{studentId}")
    public List<Internship> findByStudentId(@PathVariable int studentId,Authentication authentication)
    {
        List<Internship> internships=internshipService.findByStudentId(studentId);
        if(authentication.getPrincipal() instanceof Teacher)
            return internships;
        else
        {
            Student student=(Student)authentication.getPrincipal();
            if(student.getId()==studentId)
                return internships;
            return List.of();
        }
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        internshipService.deleteById(id);
    }
}