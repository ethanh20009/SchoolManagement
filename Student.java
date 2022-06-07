public class Student extends Person{

    private int authorityLevel;

    public Student(int age, String fName, String sName, String username, String password) {
        super(age, fName, sName, username, password);
        this.authorityLevel = 0;
    }

    public boolean hasAuthority(int authorityLevel) {
        return this.authorityLevel >= authorityLevel;
    }
    
    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

}
