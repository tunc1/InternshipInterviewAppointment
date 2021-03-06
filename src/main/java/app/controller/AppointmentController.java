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
    public List<Appointment> findByDay(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date)
    {
        return appointmentService.findByDay(date);
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
        appointmentService.saveMultiple(teacher,multipleAppointment.start,multipleAppointment.end,multipleAppointment.startTime,multipleAppointment.endTime,multipleAppointment.minute);
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