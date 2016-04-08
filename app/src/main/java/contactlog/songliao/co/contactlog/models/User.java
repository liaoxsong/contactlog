package contactlog.songliao.co.contactlog.models;

import java.util.UUID;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Integer zipCode;

    public User() {

    }

    public User(String firstName, String lastName, String dob, Integer zip) {
        this.id = UUID.randomUUID().toString();
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

    public String getId(){
        return id;
    }

}
