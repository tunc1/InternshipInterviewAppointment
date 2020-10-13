package app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Internship
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Student student;
    @Enumerated
    private InternshipLocation location;
    @Enumerated
    private InternshipType type;
    @Enumerated(value=EnumType.STRING)
    private InternshipNote note;
    @ManyToOne
    private InternshipSubject subject;
    private Date start,finish;
    private int day;
    private String companyName;
    private boolean isPublic,takeSalary,isVolunteer,takeInsurance;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public Student getStudent()
    {
        return student;
    }
    public void setStudent(Student student)
    {
        this.student=student;
    }
    public InternshipLocation getLocation()
    {
        return location;
    }
    public void setLocation(InternshipLocation location)
    {
        this.location=location;
    }
    public InternshipType getType()
    {
        return type;
    }
    public void setType(InternshipType type)
    {
        this.type=type;
    }
    public InternshipNote getNote()
    {
        return note;
    }
    public void setNote(InternshipNote note)
    {
        this.note=note;
    }
    public InternshipSubject getSubject()
    {
        return subject;
    }
    public void setSubject(InternshipSubject subject)
    {
        this.subject=subject;
    }
    public Date getStart()
    {
        return start;
    }
    public void setStart(Date start)
    {
        this.start=start;
    }
    public Date getFinish()
    {
        return finish;
    }
    public void setFinish(Date finish)
    {
        this.finish=finish;
    }
    public int getDay()
    {
        return day;
    }
    public void setDay(int day)
    {
        this.day=day;
    }
    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName=companyName;
    }
    public boolean isPublic()
    {
        return isPublic;
    }
    public void setPublic(boolean aPublic)
    {
        isPublic=aPublic;
    }
    public boolean isTakeSalary()
    {
        return takeSalary;
    }
    public void setTakeSalary(boolean takeSalary)
    {
        this.takeSalary=takeSalary;
    }
    public boolean isVolunteer()
    {
        return isVolunteer;
    }
    public void setVolunteer(boolean volunteer)
    {
        isVolunteer=volunteer;
    }
    public boolean isTakeInsurance()
    {
        return takeInsurance;
    }
    public void setTakeInsurance(boolean takeInsurance)
    {
        this.takeInsurance=takeInsurance;
    }
}