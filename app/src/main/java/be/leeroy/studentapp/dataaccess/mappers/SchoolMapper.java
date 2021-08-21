package be.leeroy.studentapp.dataaccess.mappers;

import be.leeroy.studentapp.dataaccess.dto.OptionDTO;
import be.leeroy.studentapp.dataaccess.dto.SchoolDTO;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;

public class SchoolMapper {
    private static SchoolMapper instance;

    public static SchoolMapper getInstance() {
        if (instance == null) instance = new SchoolMapper();

        return instance;
    }

    public School mapToSchool(SchoolDTO dto) {
        return new School(dto.getId(), dto.getName(), dto.getAddress(), dto.getPhonenumber());
    }

    public Option mapToOption(OptionDTO dto) {
        return new Option(new School(dto.getSchool().getId()), dto.getName(), dto.getNbyears());
    }
}
