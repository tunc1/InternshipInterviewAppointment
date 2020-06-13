package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
@Getter
public enum InternshipLocation
{
    ERASMUS("Erasmus"),ABROAD("Abroad"),DOMESTIC("Domestic");
    private String name;
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