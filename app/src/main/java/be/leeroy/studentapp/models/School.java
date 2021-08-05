package be.leeroy.studentapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class School {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String address;
    private String phonenumber;

    public School(Integer id, String name, String address, String phonenumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public School() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
