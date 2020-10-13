package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public static InternshipLocation creator(@JsonProperty String name)
    {
        switch(name.toLowerCase())
        {
            case "erasmus":
                return ERASMUS;
            case "abroad":
                return ABROAD;
            case "domestic":
                return DOMESTIC;
            default:
                return null;
        }
    }
}