package app.service;

import app.entity.InternshipSubject;
import app.enums.InternshipType;
import app.repository.InternshipSubjectRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InternshipSubjectService
{
    private InternshipSubjectRepository internSubjectRepository;
    public InternshipSubjectService(InternshipSubjectRepository internSubjectRepository)
    {
        this.internSubjectRepository=internSubjectRepository;
    }
    public InternshipSubject save(InternshipSubject internSubject)
    {
        return internSubjectRepository.save(internSubject);
    }
    public InternshipSubject update(InternshipSubject internSubject)
    {
        return internSubjectRepository.save(internSubject);
    }
    public InternshipSubject findById(int id)
    {
        return internSubjectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public List<InternshipSubject> findAll()
    {
        return internSubjectRepository.findAll();
    }
    public void deleteById(int id)
    {
        internSubjectRepository.deleteById(id);
    }
    public List<InternshipSubject> findByType(InternshipType type)
    {
        return internSubjectRepository.findByType(type);
    }
}