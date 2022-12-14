package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Teacher implements IUser
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name,surname;
    @OneToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private User user;
    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user=user;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getSurname()
    {
        return surname;
    }
    public void setSurname(String surname)
    {
        this.surname=surname;
    }
}