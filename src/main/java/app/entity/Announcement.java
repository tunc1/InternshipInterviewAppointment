package app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Announcement
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String title,content;
    private Date date;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content=content;
    }
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date=date;
    }
}