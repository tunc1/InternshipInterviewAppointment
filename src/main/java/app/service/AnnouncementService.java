package app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entity.Announcement;
import app.repository.AnnouncementRepository;

@Service
public class AnnouncementService
{
	@Autowired
	private AnnouncementRepository announcementRepository;
	public void save(Announcement announcement)
	{
		announcementRepository.save(announcement);
	}
	public void update(Announcement announcement)
	{
		announcementRepository.save(announcement);
	}
	public void deleteById(int id)
	{
		announcementRepository.deleteById(id);
	}
	public Announcement findById(int id)
	{
		return announcementRepository.findById(id).orElse(null);
	}
	public List<Announcement> findAll()
	{
		return announcementRepository.findAllByOrderByDateDesc();
	}
}