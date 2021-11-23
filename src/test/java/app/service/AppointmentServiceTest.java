package app.service;

import app.entity.Appointment;
import app.entity.IUser;
import app.entity.Student;
import app.entity.Teacher;
import app.repository.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest
{
    @Mock
    AppointmentRepository appointmentRepository;
    AppointmentService appointmentService;

    @BeforeEach
    void setUp()
    {
        appointmentService=new AppointmentService(appointmentRepository);
    }
    @Test
    void saveMultiple()
    {
        Date start=new Date();
        Date end=start;
        LocalTime startTime=LocalTime.of(9,0);
        LocalTime endTime=LocalTime.of(9,45);
        int minute=15;
        appointmentService.saveMultiple(null,start,end,startTime,endTime,minute);
        Mockito.verify(appointmentRepository,Mockito.times(4)).save(Mockito.any());
    }
    @Test
    void save()
    {
        Appointment appointment=new Appointment();
        Mockito.when(appointmentRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Appointment.class));
        Appointment saved=appointmentService.save(appointment);
        Assertions.assertEquals(saved,appointment);
    }
    @Test
    void update()
    {
        Appointment appointment=new Appointment();
        Mockito.when(appointmentRepository.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Appointment.class));
        Appointment updated=appointmentService.update(appointment);
        Assertions.assertEquals(updated,appointment);
    }
    @Test
    void deleteById()
    {
        appointmentService.deleteById(1);
        Mockito.verify(appointmentRepository).deleteById(Mockito.any());
    }
    @Test
    void findById_teacher_returnsAppointment()
    {
        IUser user=new Teacher();
        Appointment appointment=new Appointment();
        Mockito.when(appointmentRepository.findById(Mockito.any())).thenReturn(Optional.of(appointment));
        Appointment actual=appointmentService.findById(1,user);
        Assertions.assertEquals(actual,appointment);
    }
    @Test
    void findById_student_sameStudent()
    {
        Student student=new Student();
        Appointment appointment=new Appointment();
        appointment.setStudent(student);
        Mockito.when(appointmentRepository.findById(Mockito.any())).thenReturn(Optional.of(appointment));
        Appointment actual=appointmentService.findById(1,student);
        Assertions.assertEquals(actual,appointment);
    }
    @Test
    void findById_student_differentStudent_error()
    {
        Student student=new Student();
        Student student2=new Student();
        Appointment appointment=new Appointment();
        appointment.setStudent(student2);
        Mockito.when(appointmentRepository.findById(Mockito.any())).thenReturn(Optional.of(appointment));
        Appointment actual=appointmentService.findById(1,student);
        Assertions.assertEquals(actual,appointment);
    }
    @Test
    void findById_throwsEntityNotFoundException()
    {
        IUser user=Mockito.mock(IUser.class);
        Mockito.when(appointmentRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()->appointmentService.findById(1,user));
    }
    @Test
    void findAll()
    {
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentRepository.findAll()).thenReturn(appointments);
        List<Appointment> actual=appointmentService.findAll();
        Assertions.assertEquals(actual,appointments);
    }
    @Test
    void findByTeacherIdAndNotTakenOrderByDate()
    {
        int teacherId=1;
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentRepository.findByTeacherIdAndTakenOrderByDate(Mockito.anyInt(),Mockito.anyBoolean())).thenReturn(appointments);
        List<Appointment> actual=appointmentService.findByTeacherIdAndNotTakenOrderByDate(teacherId);
        Assertions.assertEquals(actual,appointments);
    }
    @Test
    void testSaveMultiple()
    {
        Date start=new Date();
        Date end=start;
        LocalTime startTime=LocalTime.of(9,0);
        LocalTime endTime=LocalTime.of(9,45);
        int minute=15;
        appointmentService.saveMultiple(null,start,end,startTime,endTime,minute);
        Mockito.verify(appointmentRepository,Mockito.times(4)).save(Mockito.any());
    }
    @Test
    void findByDay()
    {
        Date start=new Date();
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentRepository.findByDateBetween(Mockito.any(),Mockito.any())).thenReturn(appointments);
        List<Appointment> actual=appointmentService.findByDay(start);
        Assertions.assertEquals(actual,appointments);
    }
}