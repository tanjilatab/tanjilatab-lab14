import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Client(String address, int port) throws IOException {
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

    public String request(String input) throws IOException {
        out.println(input);
        return in.readLine(); 
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}
