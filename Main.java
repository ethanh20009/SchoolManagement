import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Classroom> classes;

    public Main() {
        this.teachers = new ArrayList<Teacher>();
        this.students = new ArrayList<Student>();

        load();
    }

    public void save(){
        //save teachers
        //save students

        try(ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream("teachers.dat"))){
            objectWriter.writeObject(teachers);
        }
        catch(IOException e){
            System.out.println("Error saving teachers");
        }

        try(ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream("students.dat"))){
            objectWriter.writeObject(students);
        }
        catch(IOException e){
            System.out.println("Error saving students");
        }

        try(ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream("classes.dat"))){
            objectWriter.writeObject(classes);
        }
        catch(IOException e){
            System.out.println("Error saving classes");
        }
    }

    public void load(){
        //load teachers
        //load students
        //Load classes

        this.teachers = new ArrayList<Teacher>();
        try(ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream("teachers.dat"))){
            while (objectReader.available() > 0)
            {
                this.teachers.add((Teacher) objectReader.readObject());
            }
        }
        catch(IOException e){
            System.out.println("Error loading teachers");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error loading teachers");
        }

        this.students = new ArrayList<Student>();
        try(ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream("students.dat"))){
            while (objectReader.available() > 0)
            {
                this.students.add((Student) objectReader.readObject());
            }
        }
        catch(IOException e){
            System.out.println("Error loading students");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error loading students");
        }

        this.classes = new ArrayList<Classroom>();
        try(ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream("classes.dat"))){
            while (objectReader.available() > 0)
            {
                this.classes.add((Classroom) objectReader.readObject());
            }
        }
        catch(IOException e){
            System.out.println("Error loading classes");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error loading classes");
        }
    }
}
