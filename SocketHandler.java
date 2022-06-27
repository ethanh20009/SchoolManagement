import java.io.*;
import java.net.Socket;

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

    @Override
    public void run() {
        
    }

    
}
