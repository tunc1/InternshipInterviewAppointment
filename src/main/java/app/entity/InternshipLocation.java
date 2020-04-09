package app.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InternshipLocation
{
    ERASMUS("Erasmus"),ABROAD("Yurtdışı"),DOMESTIC("Yurtiçi");
    private String name;
}