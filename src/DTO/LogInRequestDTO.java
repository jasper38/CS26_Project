package DTO;

public class LogInRequestDTO {
    private String username;
    private String password;

    public LogInRequestDTO() {}

    public LogInRequestDTO(String username, String password) {}

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
}
