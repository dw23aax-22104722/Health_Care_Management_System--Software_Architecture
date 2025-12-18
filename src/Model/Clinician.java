package Model;

public class Clinician
{
    private String clinicianId;
    private String firstName;
    private String lastName;
    private String title;
    private String speciality;

    private String phoneNumber;
    private String email;

    private String workplaceId;
    private String workplaceType;

    private String employmentStatus;
    private String startDate;

    public Clinician(
            String clinicianId,
            String firstName,
            String lastName,
            String title,
            String speciality,
            String phoneNumber,
            String email,
            String workplaceId,
            String workplaceType,
            String employmentStatus,
            String startDate
    ) {
        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    public String getClinicianId() {
        return clinicianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkplaceId() {
        return workplaceId;
    }

    public String getWorkplaceType() {
        return workplaceType;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "Clinician{" +
                "id='" + clinicianId + '\'' +
                ", name='" + getFullName() + '\'' +
                ", speciality='" + speciality + '\'' +
                ", workplace='" + workplaceId + '\'' +
                '}';
    }
}
