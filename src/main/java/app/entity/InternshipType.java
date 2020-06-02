package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
@Getter
public enum InternshipType
{
    HARDWARE("Hardware"),SOFTWARE("Software");
    private String name;
}