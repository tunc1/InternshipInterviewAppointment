package app.repository;

import app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface TeacherRepository extends JpaRepository<Teacher,Integer>
{
    UserDetails findByUsername(String username);
}