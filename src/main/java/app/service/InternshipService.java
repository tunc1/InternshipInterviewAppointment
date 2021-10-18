package app.service;

import app.entity.IUser;
import app.entity.Internship;
import app.entity.Student;
import app.entity.Teacher;
import app.exception.UnauthorizedException;
import app.repository.InternshipRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public Internship findById(int id,IUser user)
    {
        Internship internship=internshipRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(user instanceof Teacher)
            return internship;
        else
        {
            Student student=(Student)user;
            if(internship.getStudent().getId()==student.getId())
                return internship;
            else
                throw new UnauthorizedException();
        }
    }
    public List<Internship> findAll(IUser user)
    {
        if(user instanceof Teacher)
            return internshipRepository.findAll();
        else
            throw new UnauthorizedException();
    }
    public void deleteById(int id)
    {
        internshipRepository.deleteById(id);
    }
    public List<Internship> findByStudentId(int studentId,IUser user)
    {
        List<Internship> internships=internshipRepository.findByStudentId(studentId);
        if(user instanceof Teacher)
            return internships;
        else
        {
            Student student=(Student)user;
            if(student.getId()==studentId)
                return internships;
            else
                throw new UnauthorizedException();
        }
    }
}