package app.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum InternshipLocation
{
    ERASMUS("Erasmus"),ABROAD("Abroad"),DOMESTIC("Domestic");
    private String name;
    InternshipLocation(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
}