import java.util.*;
import java.io.*;
import java.net.*;

public class Client implements Runnable{
    
    private Socket socket;
    private BufferedReader inputStream;
    private BufferedWriter outputStream;
    private BufferedReader systemReader;



    
    public Client()
    {
        try{
            this.socket = new Socket("localhost", 8080);
            this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.systemReader = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(IOException e){
            //Ignore
        }
    }

    public void shutdown()
    {
        try{
            this.inputStream.close();
            this.outputStream.close();
            this.systemReader.close();
            this.socket.close();
        }
        catch(IOException e){
            //Ignore
        }
    }

    public BufferedReader getSystemReader() {
        return systemReader;
    }

    @Override
    public void run() {
        try{
            while (true)
            {
                String message = this.inputStream.readLine();
                System.out.println(message);
            }
        }
        catch(IOException e){
            //Ignore
        }
        
    }

    public static void main(String[] args)
    {
        Client client = new Client();
        Thread thread = new Thread(client);
        thread.start();
        try
        {
            BufferedReader br = client.getSystemReader();
            while(true)
            {
                String message = br.readLine();
                client.outputStream.write(message);
                client.outputStream.newLine();
                client.outputStream.flush();
            }
        }
        catch(IOException e){
            //Ignore
        }
    }
}
