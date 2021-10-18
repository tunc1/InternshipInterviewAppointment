package app.controller;

import app.entity.Appointment;
import app.entity.IUser;
import app.entity.Teacher;
import app.request.MultipleAppointmentRequest;
import app.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController
{
    private AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService)
    {
        this.appointmentService=appointmentService;
    }
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
    public Appointment findById(@PathVariable int id,Authentication authentication)
    {
        IUser user=(IUser)authentication.getPrincipal();
        return appointmentService.findById(id,user);
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