package app.service;

import app.entity.InternshipSubject;
import app.entity.InternshipType;
import app.repository.InternshipSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipSubjectService
{
    @Autowired
    private InternshipSubjectRepository internSubjectRepository;
    public void save(InternshipSubject internSubject)
    {
        internSubjectRepository.save(internSubject);
    }
    public void update(InternshipSubject internSubject)
    {
        internSubjectRepository.save(internSubject);
    }
    public InternshipSubject findById(int id)
    {
        return internSubjectRepository.findById(id).orElse(null);
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