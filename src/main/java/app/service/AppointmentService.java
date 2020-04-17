package app.service;

import app.entity.Appointment;
import app.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService
{
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void save(Appointment appointment)
    {
        appointmentRepository.save(appointment);
    }
    public void update(Appointment appointment)
    {
        appointmentRepository.save(appointment);
    }
    public Appointment findById(int id)
    {
        return appointmentRepository.findById(id).orElse(null);
    }
    public List<Appointment> findAll()
    {
        return appointmentRepository.findAll();
    }
    public void deleteById(int id)
    {
        appointmentRepository.deleteById(id);
    }
}