import java.util.ArrayList;

public class Teacher extends Person{

    private int authorityLevel;
    private ArrayList<Subject> subjects;

    public Teacher(int age, String fName, String sName, String username, String password, int authorityLevel) {
        super(age, fName, sName, username, password);
        this.authorityLevel = authorityLevel;
        subjects = new ArrayList<Subject>();
    }

    public boolean hasAuthority(int authorityLevel) {
        return this.authorityLevel >= authorityLevel;
    }

    public void setAuthorityLevel(int authorityLevel) {
        this.authorityLevel = authorityLevel;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public boolean TeachesSubject(Subject subject) {
        return subjects.contains(subject);
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
    

}