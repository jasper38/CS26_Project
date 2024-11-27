package DTO;

import java.sql.Date;

public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String sex;
    private Date birthdate;
    private String phoneNo;
    private String address;
    private String username;
    private String password;
    private String email;
    private String bankAccountType;
    private float bankAccountBalance;


    public RegistrationRequestDTO() {
        super();
    }

    public RegistrationRequestDTO(String firstName, String lastName, int age, String sex, Date birthdate, String phoneNo,
                                  String address, String username, String password, String email, String bankAccountType, float bankAccountBalance) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.birthdate = birthdate;
        this.phoneNo = phoneNo;
        this.address = address;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bankAccountType = bankAccountType;
        this.bankAccountBalance = bankAccountBalance;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public float getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(float bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }
}
