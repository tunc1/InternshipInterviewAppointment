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
    private AppointmentRepository studentRepository;
    public void save(Appointment student)
    {
        studentRepository.save(student);
    }
    public void update(Appointment student)
    {
        studentRepository.save(student);
    }
    public Appointment findById(int id)
    {
        return studentRepository.findById(id).orElse(null);
    }
    public List<Appointment> findAll()
    {
        return studentRepository.findAll();
    }
    public void deleteById(int id)
    {
        studentRepository.deleteById(id);
    }
}