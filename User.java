public interface User {
    public int getUserID();
    public String getUsername();
    public String getfName();
    public String getsName();
    public boolean validateCredentials(String username, String password);
    public boolean setPassword(String newPassword);
}
