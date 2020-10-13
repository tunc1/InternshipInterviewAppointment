package app.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum InternshipNote
{
    YT("YT"),YZ("YZ");
    InternshipNote(String note)
    {
        this.note=note;
    }
    private String note;
    public String getNote()
    {
        return note;
    }
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