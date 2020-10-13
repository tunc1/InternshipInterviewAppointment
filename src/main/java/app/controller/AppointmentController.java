package app.controller;

import app.entity.Appointment;
import app.entity.Teacher;
import app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.HOURS;

@RestController
@RequestMapping("/appointment")
public class AppointmentController
{
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping
    public Appointment save(@RequestBody Appointment appointment)
    {
        appointmentService.save(appointment);
        return appointment;
    }
    @PutMapping
    public void update(@RequestBody Appointment appointment)
    {
        appointmentService.update(appointment);
    }
    @GetMapping("/{id}")
    public Appointment findById(@PathVariable int id)
    {
        return appointmentService.findById(id);
    }
    @GetMapping
    public List<Appointment> findAll()
    {
        return appointmentService.findAll();
    }
    @GetMapping("/day/{date}")
    public List<Appointment> findByDay(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date start)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.HOUR_OF_DAY,23);
        calendar.add(Calendar.MINUTE,59);
        Date end=calendar.getTime();
        return appointmentService.findByDateBetween(start,end);
    }
    @GetMapping("/teacher/{teacherId}")
    public List<Appointment> findByTeacherIdAndNotTakenOrderByDate(@PathVariable int teacherId)
    {
        return appointmentService.findByTeacherIdAndNotTakenOrderByDate(teacherId);
    }
    @GetMapping("/student/{id}")
    public List<Appointment> findByStudentId(@PathVariable int id)
    {
        return appointmentService.findByStudentId(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        appointmentService.deleteById(id);
    }
    @PostMapping("/multiple")
    public void saveMultiple(@RequestBody MultipleAppointment multipleAppointment,Authentication authentication)
    {
        Teacher teacher=(Teacher)authentication.getPrincipal();
        long difference=Math.abs(multipleAppointment.start.getTime()-multipleAppointment.end.getTime());
        long diff=TimeUnit.DAYS.convert(difference,TimeUnit.MILLISECONDS);
        long minutes=(HOURS.between(multipleAppointment.startTime,multipleAppointment.endTime))*60/multipleAppointment.minute;
        for(int i=0;i<diff+1;i++)
        {
            for(int j=0;j<minutes+1;j++)
            {
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(multipleAppointment.start);
                calendar.add(Calendar.DATE,i);
                calendar.set(Calendar.HOUR,multipleAppointment.startTime.getHour()+(j*multipleAppointment.minute)/60);
                calendar.set(Calendar.MINUTE,multipleAppointment.minute*(j%(60/multipleAppointment.minute)));
                Appointment appointment=new Appointment();
                appointment.setDate(calendar.getTime());
                appointment.setTeacher(teacher);
                appointmentService.save(appointment);
            }
        }
    }
    private static class MultipleAppointment
    {
        private Date start,end;
        private LocalTime startTime,endTime;
        private int minute;
        public Date getStart()
        {
            return start;
        }
        public void setStart(Date start)
        {
            this.start=start;
        }
        public Date getEnd()
        {
            return end;
        }
        public void setEnd(Date end)
        {
            this.end=end;
        }
        public LocalTime getStartTime()
        {
            return startTime;
        }
        public void setStartTime(LocalTime startTime)
        {
            this.startTime=startTime;
        }
        public LocalTime getEndTime()
        {
            return endTime;
        }
        public void setEndTime(LocalTime endTime)
        {
            this.endTime=endTime;
        }
        public int getMinute()
        {
            return minute;
        }
        public void setMinute(int minute)
        {
            this.minute=minute;
        }
    }
}