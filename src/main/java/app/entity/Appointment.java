package app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Student student;
    private Date date;
    private boolean taken;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public Teacher getTeacher()
    {
        return teacher;
    }
    public void setTeacher(Teacher teacher)
    {
        this.teacher=teacher;
    }
    public Student getStudent()
    {
        return student;
    }
    public void setStudent(Student student)
    {
        this.student=student;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date=date;
    }
    public boolean isTaken()
    {
        return taken;
    }
    public void setTaken(boolean taken)
    {
        this.taken=taken;
    }
}