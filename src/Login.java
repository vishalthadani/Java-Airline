class Login {
    private int loginId;
    private String username;
    private String password;

    // Constructors
    public Login() {
    }

    public Login(int loginId, String username, String password) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
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

    // toString method
    @Override
    public String toString() {
        return "Login{" +
                "loginId=" + loginId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}