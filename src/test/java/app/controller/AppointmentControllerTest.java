package app.controller;

import app.entity.Appointment;
import app.entity.Student;
import app.entity.Teacher;
import app.request.MultipleAppointmentRequest;
import app.response.MessageResponse;
import app.service.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest
{
    @Mock
    AppointmentService appointmentService;
    AppointmentController appointmentController;

    @BeforeEach
    void setUp()
    {
        appointmentController=new AppointmentController(appointmentService);
    }
    @Test
    void save()
    {
        Appointment appointment=new Appointment();
        Mockito.when(appointmentService.save(Mockito.any())).thenAnswer(e->e.getArgument(0,Appointment.class));
        Appointment saved=appointmentController.save(appointment);
        Assertions.assertEquals(saved,appointment);
    }
    @Test
    void update()
    {
        Integer id=1;
        Appointment appointment=new Appointment();
        Mockito.when(appointmentService.update(Mockito.any())).thenAnswer(e->e.getArgument(0,Appointment.class));
        Appointment updated=appointmentController.update(appointment,id);
        Assertions.assertEquals(updated,appointment);
        Assertions.assertEquals(updated.getId(),id);
    }
    @Test
    void deleteById()
    {
        appointmentController.deleteById(1);
        Mockito.verify(appointmentService).deleteById(Mockito.anyInt());
    }
    @Test
    void findById_returnsAppointment()
    {
        Student student=new Student();
        Authentication authentication=new UsernamePasswordAuthenticationToken(student,null);
        Appointment appointment=new Appointment();
        Mockito.when(appointmentService.findById(Mockito.anyInt(),Mockito.any(Student.class))).thenReturn(appointment);
        Appointment actual=appointmentController.findById(1,authentication);
        Assertions.assertEquals(actual,appointment);
    }
    @Test
    void findAll()
    {
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentService.findAll()).thenReturn(appointments);
        List<Appointment> actual=appointmentController.findAll();
        Assertions.assertEquals(actual,appointments);
    }
    @Test
    void findByTeacherIdAndNotTakenOrderByDate()
    {
        int teacherId=1;
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentService.findByTeacherIdAndNotTakenOrderByDate(Mockito.anyInt())).thenReturn(appointments);
        List<Appointment> actual=appointmentController.findByTeacherIdAndNotTakenOrderByDate(teacherId);
        Assertions.assertEquals(actual,appointments);
    }
    @Test
    void findByStudentId()
    {
        int studentId=1;
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentService.findByStudentId(Mockito.anyInt())).thenReturn(appointments);
        List<Appointment> actual=appointmentController.findByStudentId(studentId);
        Assertions.assertEquals(actual,appointments);
    }
    @Test
    void saveMultiple()
    {
        Teacher teacher=new Teacher();
        Authentication authentication=new UsernamePasswordAuthenticationToken(teacher,null);
        MultipleAppointmentRequest multipleAppointmentRequest=new MultipleAppointmentRequest();
        MessageResponse messageResponse=appointmentController.saveMultiple(multipleAppointmentRequest,authentication);
        Assertions.assertTrue(messageResponse.getMessage().equals("Saved"));
    }
    @Test
    void findByDay()
    {
        Date date=new Date();
        List<Appointment> appointments=List.of(new Appointment());
        Mockito.when(appointmentService.findByDay(Mockito.any(Date.class))).thenReturn(appointments);
        List<Appointment> actual=appointmentController.findByDay(date);
        Assertions.assertEquals(actual,appointments);
    }
}