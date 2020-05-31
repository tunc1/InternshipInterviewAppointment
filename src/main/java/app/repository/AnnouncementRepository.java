package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Announcement;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer>
{
    List<Announcement> findAllByOrderByDateDesc();
}