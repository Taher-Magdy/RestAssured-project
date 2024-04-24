package QAcard;

public class PojoLogin {
    // Keys should be the same as the keys in the json file
    // keys should be private
    private String email;
    private String password;

    public PojoLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PojoLogin() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
