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
        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serve(int clients){
        try{
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
        private ArrayList<Integer> factors = new ArrayList<>();

        public ClientHandler(Socket socket) {
            this.socket = socket;
            this.connectTime = LocalDateTime.now();
            connectedTimes.add(connectTime); 
        }
        
        public void disconnect(){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                String pass = br.readLine();
                if(!pass.equals("12345")){
                    socket.close();
                    return;
                }

                int num = Integer.parseInt(br.readLine());
                factors.add(1);
                factors.add(num);

                for(int i = 2; i <= (int) Math.sqrt(num); i++){
                    if(num % i == 0){
                        factors.add(i);
                        if (num != (i * i)) {
                            factors.add(num/i);
                        }
                    }
                }

                pw.println("The number " + num + " has " + factors.size() + " factors");
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            this.disconnect();
        }

    }

}
