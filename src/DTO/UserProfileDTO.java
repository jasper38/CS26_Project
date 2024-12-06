package DTO;

public class UserProfileDTO {
    private String fullName;
    private String birthDate;
    private int age;
    private String sex;
    private String username;
    private String contactNumber;
    private String email;
    private int bankAccountNumberID;

    public UserProfileDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBankAccountNumberID() {
        return bankAccountNumberID;
    }

    public void setBankAccountNumberID(int bankAccountNumberID) {
        this.bankAccountNumberID = bankAccountNumberID;
    }

    @Override
    public String toString() {
        return "UserProfileDTO{" +
                "fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", username='" + username + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", bankAccountNumberID=" + bankAccountNumberID +
                '}';
    }
}
