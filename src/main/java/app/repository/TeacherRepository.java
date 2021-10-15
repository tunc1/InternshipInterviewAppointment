package app.repository;

import app.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer>
{
	long count();
	Teacher findByUserUsername(String username);
}