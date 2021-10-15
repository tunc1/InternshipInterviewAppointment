package app.request;

import java.time.LocalTime;
import java.util.Date;

public class MultipleAppointmentRequest
{
    private Date start,end;
    private LocalTime startTime,endTime;
    private int minute;
    public Date getStart()
    {
        return start;
    }
    public void setStart(Date start)
    {
        this.start=start;
    }
    public Date getEnd()
    {
        return end;
    }
    public void setEnd(Date end)
    {
        this.end=end;
    }
    public LocalTime getStartTime()
    {
        return startTime;
    }
    public void setStartTime(LocalTime startTime)
    {
        this.startTime=startTime;
    }
    public LocalTime getEndTime()
    {
        return endTime;
    }
    public void setEndTime(LocalTime endTime)
    {
        this.endTime=endTime;
    }
    public int getMinute()
    {
        return minute;
    }
    public void setMinute(int minute)
    {
        this.minute=minute;
    }
}