package app.controller;

import app.entity.InternshipSubject;
import app.service.InternshipSubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class InternshipSubjectControllerTest
{
    @Mock
    InternshipSubjectService internshipSubjectService;
    InternshipSubjectController internshipSubjectController;

    @BeforeEach
    void setUp()
    {
        internshipSubjectController=new InternshipSubjectController(internshipSubjectService);
    }
    @Test
    void save()
    {
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,InternshipSubject.class));
        InternshipSubject saved=internshipSubjectController.save(internshipSubject);
        Assertions.assertEquals(saved,internshipSubject);
    }
    @Test
    void update()
    {
        Integer id=1;
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,InternshipSubject.class));
        InternshipSubject updated=internshipSubjectController.update(internshipSubject,id);
        Assertions.assertEquals(updated,internshipSubject);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        internshipSubjectController.deleteById(1);
        Mockito.verify(internshipSubjectService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsInternshipSubject()
    {
        InternshipSubject internshipSubject=new InternshipSubject();
        Mockito.when(internshipSubjectService.findById(Mockito.anyInt())).thenReturn(internshipSubject);
        InternshipSubject actual=internshipSubjectController.findById(1);
        Assertions.assertEquals(actual,internshipSubject);
    }
    @Test
    void findAll()
    {
        List<InternshipSubject> internshipSubjects=List.of(new InternshipSubject());
        Mockito.when(internshipSubjectService.findAll()).thenReturn(internshipSubjects);
        List<InternshipSubject> actual=internshipSubjectController.findAll();
        Assertions.assertEquals(actual,internshipSubjects);
    }
    @Test
    void findByType()
    {
        List<InternshipSubject> internshipSubjects=List.of(new InternshipSubject());
        Mockito.when(internshipSubjectService.findByType(Mockito.any())).thenReturn(internshipSubjects);
        List<InternshipSubject> actual=internshipSubjectController.findByType("hardware");
        Assertions.assertEquals(actual,internshipSubjects);
    }
}