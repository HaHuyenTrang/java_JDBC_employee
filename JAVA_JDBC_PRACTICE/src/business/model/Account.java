package business.model;

public class Account {
    private String username;
    private String password;
    private AccountStatus status;

    public enum AccountStatus {
        ACTIVE, INACTIVE
    }

    // Constructors
    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = AccountStatus.ACTIVE;
    }

    // Getters và Setters
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

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    // Login
    public boolean login(String username, String password) {
        return this.username.equals(username) &&
                this.password.equals(password) &&
                this.status == AccountStatus.ACTIVE;
    }
    // Logout
    public void logout() {

    }
}
