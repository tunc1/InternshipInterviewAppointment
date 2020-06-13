package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
@Getter
public enum InternshipNote
{
    YT("YT"),YZ("YZ");
    private String note;
    @JsonCreator
    public static InternshipNote creator(@JsonProperty String name)
    {
        switch(name.toLowerCase())
        {
            case "yt":
                return YT;
            case "yz":
                return YZ;
            default:
                return null;
        }
    }
}