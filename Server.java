import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.function.Function;

public class Server implements Runnable{

    private Main schoolMain;
    private ServerSocket socket;
    private ArrayList<SocketHandler> sockets;

    public Server()
    {
        this.schoolMain = new Main();
        this.sockets = new ArrayList<SocketHandler>();
    }

    public void shutdown()
    {
        //TODO
    }

    public User login(String username, String password)
    {
        Teacher teacher = schoolMain.loginTeacher(username, password);
        if (teacher != null)
        {
            return teacher;
        }
        Student student = schoolMain.loginStudent(username, password);
        if (student != null)
        {
            return student;
        }
        return null;
    }

    @Override
    public void run() {
        try{
            this.socket = new ServerSocket(8080);
            while (true)
            {
                Socket connection = this.socket.accept();
                SocketHandler handler = new SocketHandler(this, connection);
                Thread thread = new Thread(handler);
                thread.start();
                sockets.add(handler);
                
                
            }
        }
        catch (IOException e)
        {
            //Ignore
        }
        
    }

    public void printCommands(HashMap<String, Runnable> c)
    {
        System.out.print("Commands: ");
        for (String key : c.keySet())
        {
            System.out.print(key + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        Server server = new Server();
        Thread t = new Thread(server);
        t.start();

        HashMap<String, Runnable> commands = new HashMap<String, Runnable>();
        commands.put("help", () -> server.printCommands(commands));

        
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            
            while (true)
            {
                String command = br.readLine();
                Runnable func = commands.get(command);
                if (func == null) {continue;}
                func.run();

            }
        }
        catch(IOException e)
        {
            System.out.println("Server Shut down");
        }
        
    }
    
}
