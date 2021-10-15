package app.service;

import app.entity.Internship;
import app.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService
{
    private InternshipRepository internshipRepository;
    public InternshipService(InternshipRepository internshipRepository)
    {
        this.internshipRepository=internshipRepository;
    }
    public Internship save(Internship internship)
    {
        return internshipRepository.save(internship);
    }
    public Internship update(Internship internship)
    {
        return internshipRepository.save(internship);
    }
    public Internship findById(int id)
    {
        return internshipRepository.findById(id).orElse(null);
    }
    public List<Internship> findAll()
    {
        return internshipRepository.findAll();
    }
    public void deleteById(int id)
    {
        internshipRepository.deleteById(id);
    }
    public List<Internship> findByStudentId(int studentId)
    {
        return internshipRepository.findByStudentId(studentId);
    }
}