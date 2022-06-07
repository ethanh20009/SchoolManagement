import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Classroom implements Serializable{

    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private Subject subject;
    private Date startDate;
    private Date endDate;

    public Classroom(ArrayList<Teacher> teachers, ArrayList<Student> students, Subject subject, Date startDate, Date endDate) {
        this.teachers = teachers;
        this.students = students;
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public boolean getLessonIsInDate(Date date) {
        return startDate.before(date) && endDate.after(date);
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
