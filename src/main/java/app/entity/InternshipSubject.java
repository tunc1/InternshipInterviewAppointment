package app.entity;

import javax.persistence.*;

@Entity
public class InternshipSubject
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Enumerated
    private InternshipType type;
    private String name;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public InternshipType getType()
    {
        return type;
    }
    public void setType(InternshipType type)
    {
        this.type=type;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
}