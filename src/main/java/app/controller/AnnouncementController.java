package app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.entity.Announcement;
import app.service.AnnouncementService;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController
{
	private AnnouncementService announcementService;
	public AnnouncementController(AnnouncementService announcementService)
	{
		this.announcementService=announcementService;
	}
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
	@ResponseStatus(code=HttpStatus.CREATED)
	public Announcement save(@RequestBody Announcement announcement)
	{
		return announcementService.save(announcement);
	}
	@PutMapping("/{id}")
	public Announcement update(@RequestBody Announcement announcement,@PathVariable Integer id)
	{
		announcement.setId(id);
		return announcementService.update(announcement);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable int id)
	{
		announcementService.deleteById(id);
	}
}