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
        socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public Socket getSocket() {
        return socket;
    }

    public void handshake() {
        out.println("12345");
    }

    public void disconnect() {
        in.close();
        out.close();
        socket.close();
    }


}