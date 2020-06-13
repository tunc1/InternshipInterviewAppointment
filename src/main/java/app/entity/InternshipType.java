package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
@Getter
public enum InternshipType
{
    HARDWARE("Hardware"),SOFTWARE("Software");
    private String name;
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