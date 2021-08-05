package be.leeroy.studentapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Role {
    @PrimaryKey
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }
}
