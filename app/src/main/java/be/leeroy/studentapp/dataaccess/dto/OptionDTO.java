package be.leeroy.studentapp.dataaccess.dto;

import be.leeroy.studentapp.models.School;

public class OptionDTO {
    private School school;
    private String name;
    private Integer nbyears;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbyears() {
        return nbyears;
    }

    public void setNbyears(Integer nbyears) {
        this.nbyears = nbyears;
    }
}
