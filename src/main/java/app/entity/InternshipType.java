package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum InternshipType
{
    HARDWARE("Hardware"),SOFTWARE("Software");
    private String name;
    InternshipType(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    @JsonCreator
    public static InternshipType creator(@JsonProperty String name)
    {
        switch(name.toLowerCase())
        {
            case "hardware":
                return HARDWARE;
            case "software":
                return SOFTWARE;
            default:
                return null;
        }
    }
}