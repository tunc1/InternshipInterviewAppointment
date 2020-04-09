package app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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
    @ManyToOne
    private InternshipSubject subject;
    private Date start,finish;
    private int day;
    private String companyName;
    private boolean isPublic,takeSalary,isVolunteer,takeInsurance;
}