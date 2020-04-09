package app.repository;

import app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface StudentRepository extends JpaRepository<Student,Integer>
{
    UserDetails findByUsername(String username);
}