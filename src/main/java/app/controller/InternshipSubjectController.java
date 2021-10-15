package app.controller;

import app.entity.InternshipSubject;
import app.enums.InternshipType;
import app.service.InternshipSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internshipSubject")
public class InternshipSubjectController
{
    @Autowired
    private InternshipSubjectService internshipSubjectService;
    @PostMapping
    @ResponseStatus(code=HttpStatus.CREATED)
    public InternshipSubject save(@RequestBody InternshipSubject internshipSubject)
    {
        return internshipSubjectService.save(internshipSubject);
    }
    @PutMapping("/{id}")
    public InternshipSubject update(@RequestBody InternshipSubject internshipSubject,@PathVariable Integer id)
    {
        internshipSubject.setId(id);
        return internshipSubjectService.update(internshipSubject);
    }
    @GetMapping("/{id}")
    public InternshipSubject findById(@PathVariable int id)
    {
        return internshipSubjectService.findById(id);
    }
    @GetMapping
    public List<InternshipSubject> findAll()
    {
        return internshipSubjectService.findAll();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        internshipSubjectService.deleteById(id);
    }
    @GetMapping(params="type")
    public List<InternshipSubject> findByType(String type)
    {
        return internshipSubjectService.findByType(InternshipType.creator(type));
    }
}