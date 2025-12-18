package Model;

public class Clinician
{
    private String id;
    private String name;
    private String specialization;


    public Clinician(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    @Override
    public String toString() {
        return "Professional{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
