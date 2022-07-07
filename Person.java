import java.io.Serializable;

public abstract class Person implements Serializable, User{

    private static int peopleCount = 0;

    private int age;
    private String fName;
    private String sName;
    private int userID;
    private String username;
    private String password;


    public Person(int age, String fName, String sName, String username, String password)
    {
        this.age = age;
        this.fName = fName;
        this.sName = sName;
        this.username = username;
        this.password = password;
        this.userID = peopleCount;
        peopleCount++;

    }

    public int getAge() {
        return age;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }

    public boolean validateCredentials(String username, String password)
    {
        return (username.equals(this.username) && this.password.equals(password));
    }

    public boolean setPassword(String newPassword)
    {
        if (newPassword.length() < 8) {return false;}
        //Check if password contains lower case, uppercase, numbers and special characters
        if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return false;
        }
        
        this.password = newPassword;
        return true;
    }

}
