/*
UML Diagram specifics:
- Socket: Socket
+ Client (String int)
+ getSocket(): Socket
+ handshake(): void
+ disconnect(): void
*/
import java.util.*;


public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client (String address, int port) {

    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() {

    }

    public void disconnect(){
        
    }


}