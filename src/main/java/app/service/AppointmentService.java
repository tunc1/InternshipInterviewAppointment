package app.service;

import app.entity.Appointment;
import app.entity.IUser;
import app.entity.Student;
import app.entity.Teacher;
import app.exception.UnauthorizedException;
import app.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class AppointmentService
{
    private AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository=appointmentRepository;
    }
    public Appointment save(Appointment appointment)
    {
        return appointmentRepository.save(appointment);
    }
    public Appointment update(Appointment appointment)
    {
        return appointmentRepository.save(appointment);
    }
    public Appointment findById(int id,IUser user)
    {
        Appointment appointment=appointmentRepository.findById(id).orElse(null);
        if(user instanceof Teacher)
            return appointment;
        else
        {
            Student student=(Student)user;
            if(student.getId()==appointment.getStudent().getId())
                return appointment;
            else
                throw new UnauthorizedException();
        }
    }
    public List<Appointment> findAll()
    {
        return appointmentRepository.findAll();
    }
    public void deleteById(int id)
    {
        appointmentRepository.deleteById(id);
    }
    public List<Appointment> findByTeacherIdAndNotTakenOrderByDate(int teacherId)
    {
        return appointmentRepository.findByTeacherIdAndTakenOrderByDate(teacherId,false);
    }
    public List<Appointment> findByStudentId(int id)
    {
        return appointmentRepository.findByStudentId(id);
    }
    public void saveMultiple(Teacher teacher,Date start,Date end,LocalTime startTime,LocalTime endTime,int minute)
    {
        long days=TimeUnit.DAYS.convert(start.getTime()-end.getTime(),TimeUnit.MILLISECONDS);
        long minutes=MINUTES.between(startTime,endTime)/minute;
        for(int i=0;i<days+1;i++)
        {
            for(int j=0;j<minutes+1;j++)
            {
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(start);
                calendar.add(Calendar.DATE,i);
                calendar.set(Calendar.HOUR,startTime.getHour()+(j*minute)/60);
                calendar.set(Calendar.MINUTE,minute*(j%(60/minute)));
                Appointment appointment=new Appointment();
                appointment.setDate(calendar.getTime());
                appointment.setTeacher(teacher);
                appointmentRepository.save(appointment);
            }
        }
    }
    public List<Appointment> findByDay(Date start)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.HOUR_OF_DAY,23);
        calendar.add(Calendar.MINUTE,59);
        Date end=calendar.getTime();
        return appointmentRepository.findByDateBetween(start,end);
    }
}