package app.repository;

import app.entity.InternshipSubject;
import app.entity.InternshipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipSubjectRepository extends JpaRepository<InternshipSubject,Integer>
{
    List<InternshipSubject> findByType(InternshipType type);
}