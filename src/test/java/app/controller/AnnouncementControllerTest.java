package app.controller;

import app.entity.Announcement;
import app.service.AnnouncementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnnouncementControllerTest
{
    @Mock
    AnnouncementService announcementService;
    AnnouncementController announcementController;

    @BeforeEach
    void setUp()
    {
        announcementController=new AnnouncementController(announcementService);
    }
    @Test
    void save()
    {
        Announcement announcement=new Announcement();
        Mockito.when(announcementService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Announcement.class));
        Announcement saved=announcementController.save(announcement);
        Assertions.assertEquals(saved,announcement);
    }
    @Test
    void update()
    {
        Integer id=1;
        Announcement announcement=new Announcement();
        Mockito.when(announcementService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Announcement.class));
        Announcement updated=announcementController.update(announcement,id);
        Assertions.assertEquals(updated,announcement);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        announcementController.deleteById(1);
        Mockito.verify(announcementService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsAnnouncement()
    {
        Announcement announcement=new Announcement();
        Mockito.when(announcementService.findById(Mockito.anyInt())).thenReturn(announcement);
        Announcement actual=announcementController.findById(1);
        Assertions.assertEquals(actual,announcement);
    }
    @Test
    void findAll()
    {
        List<Announcement> announcements=List.of(new Announcement());
        Mockito.when(announcementService.findAll()).thenReturn(announcements);
        List<Announcement> actual=announcementController.findAll();
        Assertions.assertEquals(actual,announcements);
    }
}