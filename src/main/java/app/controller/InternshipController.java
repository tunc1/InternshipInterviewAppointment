package app.controller;

import app.entity.Internship;
import app.entity.Student;
import app.entity.Teacher;
import app.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(code=HttpStatus.CREATED)
    public Internship save(@RequestBody Internship internship,Authentication authentication)
    {
        Student student=(Student)authentication.getPrincipal();
        internship.setStudent(student);
        return internshipService.save(internship);
    }
    @PutMapping("/{id}")
    public Internship update(@RequestBody Internship internship,@PathVariable Integer id)
    {
        internship.setId(id);
        return internshipService.update(internship);
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
    @GetMapping(params="studentId")
    public List<Internship> findByStudentId(int studentId,Authentication authentication)
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
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        internshipService.deleteById(id);
    }
}