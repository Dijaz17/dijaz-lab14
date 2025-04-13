import java.net.Socket;

public class Client{
    private Socket socket;
    public String host;
    public int port;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }   

    public Socket getSocket(){
        return this.socket;
    }

    public void handshake(){
        
    }

    public String request(String input){
        return "";
    }

    public void disconnect(){

    }
}