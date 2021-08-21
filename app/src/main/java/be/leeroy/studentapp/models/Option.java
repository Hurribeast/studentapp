package be.leeroy.studentapp.models;

public class Option {

    private School school;
    private String name;
    private int nbYears;

    public Option(School school, String name, int nbYears){
        this.school = school;
        this.name = name;
        this.nbYears = nbYears;
    }

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

    public int getNbYears() {
        return nbYears;
    }

    public void setNbYears(int nbYears) {
        this.nbYears = nbYears;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
