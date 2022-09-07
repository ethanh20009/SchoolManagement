import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class SocketHandler implements Runnable{
    private Socket socket;
    private Server parent;

    private BufferedReader inputStream;
    private BufferedWriter outputStream;

    public void shutdown()
    {
        try{

            //Notify user?

            this.inputStream.close();
            this.outputStream.close();


            this.socket.close();
        }
        catch(IOException e)
        {
            //Ignore
        }
    }

    public SocketHandler(Server parent, Socket socket)
    {
        this.parent = parent;
        this.socket = socket;
        try{
            this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch(IOException e){
            //Ignore
        }
    }

    private void sendMessage(String message)
    {
        try{
            this.outputStream.write(message);
            this.outputStream.newLine();
            this.outputStream.flush();
        }
        catch(IOException e){
            //Ignore
        }
    }



    private void login () throws IOException
    {
        sendMessage("Login...");
        sendMessage("Username:");
        try{
            String username = this.inputStream.readLine();
            sendMessage("Password:");
            String password = this.inputStream.readLine();
            User user = parent.login(username, password);
            if (user != null)
            {
                sendMessage("Login successful!, logged in as a " + (user instanceof Teacher ? "teacher" : "student"));
                sendMessage("Welcome " + user.getUsername() + "!");
                if (user instanceof Teacher)
                {
                    teacher((Teacher)user);
                }
                else if (user instanceof Student)
                {
                    student((Student)user);
                }
                else{
                    sendMessage("Something went wrong. :(");
                }
            }
            else
            {
                sendMessage("Login failed!");
            }
        }
        catch(IOException e){
            throw e;
        }
    }

    @Override
    public void run() {
        try{
            while (true)
            {
                login();
            }

        }
        catch(IOException e)
        {
            //Ignore
        }
        
    }

    void getClassesIn(Person person)
    {
        sendMessage("Class Test");
    }

    void getAndRunCommands(HashMap<String, Runnable> commands) throws IOException
    {
        try{
            while (true)
            {
                sendMessage("Enter Command...");
                String userCommand = this.inputStream.readLine();
                Runnable func = commands.get(userCommand);
                if (func != null)
                {
                    func.run();
                }
                else{
                    sendMessage("Invalid command.");
                }
            }
            
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    public void teacher(Teacher teacher) throws IOException
    {
        HashMap<String, Runnable> commands = new HashMap<>();
        commands.put("get classes", () -> getClassesIn(teacher));
        try{
            getAndRunCommands(commands);
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    public void student(Student student) throws IOException
    {
        HashMap<String, Runnable> commands = new HashMap<>();
        commands.put("get classes", () -> getClassesIn(student));
        try{
            getAndRunCommands(commands);
        }
        catch(IOException e)
        {
            throw e;
        }
    }

    
}
