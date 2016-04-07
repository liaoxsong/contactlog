package contactlog.songliao.co.contactlog.models;

public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer zipCode;

    public Person() {

    }

    public Person(String firstName, String lastName, String dob, Integer zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dob;
        this.zipCode = zip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String date) {
        this.dateOfBirth = date;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zip) {
        this.zipCode = zip;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
