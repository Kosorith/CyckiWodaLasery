package pl.lodz.p.ftims.geocaching.model;

import java.util.Calendar;

/**
 * Created by michal on 11/19/14.
 */
public class Profile {

    private String firstName;
    private String lastName;
    private Calendar birthDate;
//    private String bogWieCo;

    public Profile() {
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

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }
}
