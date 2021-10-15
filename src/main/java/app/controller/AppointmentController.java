package app.controller;

import app.entity.Appointment;
import app.entity.Teacher;
import app.request.MultipleAppointmentRequest;
import app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(code=HttpStatus.CREATED)
    public Appointment save(@RequestBody Appointment appointment)
    {
        return appointmentService.save(appointment);
    }
    @PutMapping("/{id}")
    public Appointment update(@RequestBody Appointment appointment,@PathVariable Integer id)
    {
        appointment.setId(id);
        return appointmentService.update(appointment);
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
    @GetMapping(params="day")
    public List<Appointment> findByDay(@DateTimeFormat(pattern="yyyy-MM-dd") Date day)
    {
        return appointmentService.findByDay(day);
    }
    @GetMapping(params="teacherId")
    public List<Appointment> findByTeacherIdAndNotTakenOrderByDate(int teacherId)
    {
        return appointmentService.findByTeacherIdAndNotTakenOrderByDate(teacherId);
    }
    @GetMapping(params="studentId")
    public List<Appointment> findByStudentId(int studentId)
    {
        return appointmentService.findByStudentId(studentId);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id)
    {
        appointmentService.deleteById(id);
    }
    @PostMapping("/multiple")
    public void saveMultiple(@RequestBody MultipleAppointmentRequest multipleAppointmentRequest,Authentication authentication)
    {
        Teacher teacher=(Teacher)authentication.getPrincipal();
        appointmentService.saveMultiple(teacher
                ,multipleAppointmentRequest.getStart()
                ,multipleAppointmentRequest.getEnd()
                ,multipleAppointmentRequest.getStartTime()
                ,multipleAppointmentRequest.getEndTime()
                ,multipleAppointmentRequest.getMinute());
    }
}