package app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InternshipSubject
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Enumerated
    private InternshipType type;
    private String name;
}