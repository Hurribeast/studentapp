package be.leeroy.studentapp.dataaccess.dto;

import com.squareup.moshi.Json;

public class OptionDTO {

    private String name;
    @Json(name = "nbyears")
    private int nbYears;

    @Json(name = "school")
    private int schoolId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbYears() {
        return nbYears;
    }

    public void setNbYears(int nbYears) {
        this.nbYears = nbYears;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }
}
