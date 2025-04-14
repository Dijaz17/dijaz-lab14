import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
    public String host;
    public int port;
    private Socket socket;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    public Socket getSocket(){
        return this.socket;
    }

    public void handshake(){
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("12345");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String request(String input){
        String finalReq = "";
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(input);
            pw.flush();
            
            BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
            finalReq = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalReq;
        
    }

    public void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}