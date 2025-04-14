import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {
    public int port;
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();
    private ServerSocket sock;
    
    public Server(int port){
        this.port = port;
    }

    public void serve(int clients){
        try {
            sock = new ServerSocket(port);

            for(int i = 0; i < clients; ++i){
                ClientHandler clientHandler = new ClientHandler(sock.accept());
                clientHandler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes(){
        return connectedTimes;
    }

    public void disconnect(){
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;
        private LocalDateTime connectTime;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            this.connectTime = LocalDateTime.now();
            connectedTimes.add(connectTime); 
        }

        @Override
        public void run() {
            
        }

    }

}
