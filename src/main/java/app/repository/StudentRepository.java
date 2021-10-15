package app.repository;

import app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>
{
    Student findByUserUsername(String username);
}