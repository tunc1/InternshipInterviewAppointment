package app.service;

import app.entity.Announcement;
import app.repository.AnnouncementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AnnouncementServiceTest
{
    @Mock
    AnnouncementRepository announcementRepository;
    AnnouncementService announcementService;

    @BeforeEach
    void setUp()
    {
        announcementService=new AnnouncementService(announcementRepository);
    }
    @Test
    void save()
    {
        Announcement announcement=new Announcement();
        Mockito.when(announcementRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Announcement.class));
        Announcement saved=announcementService.save(announcement);
        Assertions.assertEquals(saved,announcement);
    }
    @Test
    void update()
    {
        Announcement announcement=new Announcement();
        Mockito.when(announcementRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Announcement.class));
        Announcement updated=announcementService.update(announcement);
        Assertions.assertEquals(updated,announcement);
    }
    @Test
    void deleteById()
    {
        announcementService.deleteById(1);
        Mockito.verify(announcementRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsAnnouncement()
    {
        Announcement announcement=new Announcement();
        Mockito.when(announcementRepository.findById(Mockito.any())).thenReturn(Optional.of(announcement));
        Announcement actual=announcementService.findById(1);
        Assertions.assertEquals(actual,announcement);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        Mockito.when(announcementRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->announcementService.findById(1));
    }
    @Test
    void findAll()
    {
        List<Announcement> announcements=List.of(new Announcement());
        Mockito.when(announcementRepository.findAllByOrderByDateDesc()).thenReturn(announcements);
        List<Announcement> actual=announcementService.findAll();
        Assertions.assertEquals(actual,announcements);
    }
}