package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import app.entity.Announcement;
import app.service.AnnouncementService;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController
{
	@Autowired
	private AnnouncementService announcementService;
	@GetMapping
	public List<Announcement> findAll()
	{
		return announcementService.findAll();
	}
	@GetMapping("/{id}")
	public Announcement findById(@PathVariable int id)
	{
		return announcementService.findById(id);
	}
	@PostMapping
	public Announcement save(@RequestBody Announcement announcement)
	{
		announcementService.save(announcement);
		return announcement;
	}
	@PutMapping
	public void update(@RequestBody Announcement announcement)
	{
		announcementService.update(announcement);
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id)
	{
		announcementService.deleteById(id);
	}
}