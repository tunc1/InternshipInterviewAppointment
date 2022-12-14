package app.service;

import app.entity.InternshipSubject;
import app.enums.InternshipType;
import app.repository.InternshipSubjectRepository;
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
class InternshipSubjectServiceTest
{
    @Mock
    InternshipSubjectRepository internshipSubjectRepository;
    InternshipSubjectService internshipSubjectService;

    @BeforeEach
    void setUp()
    {
        internshipSubjectService=new InternshipSubjectService(internshipSubjectRepository);
    }
    @Test
    void save()
    {
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,InternshipSubject.class));
        InternshipSubject saved=internshipSubjectService.save(internshipSubject);
        Assertions.assertEquals(saved,internshipSubject);
    }
    @Test
    void update()
    {
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,InternshipSubject.class));
        InternshipSubject updated=internshipSubjectService.update(internshipSubject);
        Assertions.assertEquals(updated,internshipSubject);
    }
    @Test
    void deleteById()
    {
        internshipSubjectService.deleteById(1);
        Mockito.verify(internshipSubjectRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_returnsInternshipSubject()
    {
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectRepository.findById(Mockito.any())).thenReturn(Optional.of(internshipSubject));
        InternshipSubject actual=internshipSubjectService.findById(1);
        Assertions.assertEquals(actual,internshipSubject);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        Mockito.when(internshipSubjectRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->internshipSubjectService.findById(1));
    }
    @Test
    void findAll()
    {
        List<InternshipSubject> internshipSubjects=List.of(new InternshipSubject());
        Mockito.when(internshipSubjectRepository.findAll()).thenReturn(internshipSubjects);
        List<InternshipSubject> actual=internshipSubjectService.findAll();
        Assertions.assertEquals(actual,internshipSubjects);
    }
    @Test
    void findByType()
    {
        InternshipType internshipType=InternshipType.HARDWARE;
        List<InternshipSubject> internshipSubjects=List.of(new InternshipSubject());
        Mockito.when(internshipSubjectRepository.findByType(Mockito.any())).thenReturn(internshipSubjects);
        List<InternshipSubject> actual=internshipSubjectService.findByType(internshipType);
        Assertions.assertEquals(actual,internshipSubjects);
    }
}