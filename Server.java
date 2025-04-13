import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {
    public int port;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();
    
    public Server(int port){
        this.port = port;
    }

    public void serve(int clients){

    }

    public ArrayList<LocalDateTime> getConnectedTimes(){
        return connectedTimes;
    }

    public void disconnect(){

    }

    private class ClientHandler {
        private Socket socket;
        private LocalDateTime connectTime;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            this.connectTime = LocalDateTime.now();
            connectedTimes.add(connectTime); 
        }

    }

}
