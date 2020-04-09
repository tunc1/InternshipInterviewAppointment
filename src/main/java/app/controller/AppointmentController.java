package app.controller;

import app.entity.Appointment;
import app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id)
    {
        appointmentService.deleteById(id);
    }
}