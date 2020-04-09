package app.repository;

import app.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship,Integer>
{
    List<Internship> findByStudentId(int studentId);
}