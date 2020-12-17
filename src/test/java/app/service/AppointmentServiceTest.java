package app.service;

import app.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes={AppointmentService.class})
class AppointmentServiceTest
{
    @Autowired
    AppointmentService appointmentService;
    @MockBean
    AppointmentRepository appointmentRepository;
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
}