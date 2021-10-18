package app.controller;

import app.entity.IUser;
import app.entity.Internship;
import app.entity.Student;
import app.service.InternshipService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship")
public class InternshipController
{
    private InternshipService internshipService;
    public InternshipController(InternshipService internshipService)
    {
        this.internshipService=internshipService;
    }
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
        IUser user=(IUser)authentication.getPrincipal();
        return internshipService.findById(id,user);
    }
    @GetMapping
    public List<Internship> findAll(Authentication authentication)
    {
        IUser user=(IUser)authentication.getPrincipal();
        return internshipService.findAll(user);
    }
    @GetMapping(params="studentId")
    public List<Internship> findByStudentId(int studentId,Authentication authentication)
    {
        IUser user=(IUser)authentication.getPrincipal();
        return internshipService.findByStudentId(studentId,user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        internshipService.deleteById(id);
    }
}