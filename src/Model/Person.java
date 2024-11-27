package Model;

import java.sql.Date;

public class Person {
    private int personID;
    private String firstName;
    private String lastName;
    private int age;
    private Date birthdate;
    private String sex;
    private String phoneNum;
    private String address;

    public Person() {}

    public Person(int personID, String firstName, String lastName, int age, Date birthdate, String sex, String phoneNum, String address) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthdate = birthdate;
        this.sex = sex;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
