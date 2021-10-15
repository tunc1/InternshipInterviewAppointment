package app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import app.entity.Announcement;
import app.repository.AnnouncementRepository;

@Service
public class AnnouncementService
{
	private AnnouncementRepository announcementRepository;
	public AnnouncementService(AnnouncementRepository announcementRepository)
	{
		this.announcementRepository=announcementRepository;
	}
	public Announcement save(Announcement announcement)
	{
		return announcementRepository.save(announcement);
	}
	public Announcement update(Announcement announcement)
	{
		return announcementRepository.save(announcement);
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