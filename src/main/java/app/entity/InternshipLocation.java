package app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
@Getter
public enum InternshipLocation
{
    ERASMUS("Erasmus"),ABROAD("Yurtdışı"),DOMESTIC("Yurtiçi");
    private String name;
}