import java.io.*;
import java.net.*;
import java.time.*;
import java.util.*;

public class Server {
    private ArrayList<LocalDateTime> connectedTimes = new ArrayList<>();
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void serve(int numClients) {
        for (int i = 0; i < numClients; i++) {
            try {
                Socket clientSocket = serverSocket.accept();
                connectedTimes.add(LocalDateTime.now());
                Thread t = new ClientHandler(clientSocket);
                t.start();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<LocalDateTime> getConnectedTimes() {
        return connectedTimes;
    }

    public void disconnect() {
        try {
            serverSocket.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countFactors(int num) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                int otherFactor = num / i;
                if (i == otherFactor) {
                    count += 1; 
                } else {
                    count += 2; 
                }
            }
        }
        return count;
    }

    private class ClientHandler extends Thread {
        private Socket client;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
                String handshake = in.readLine();
                if (!"12345".equals(handshake)) {
                    out.println("couldn't handshake");
                    client.close();
                    return;
                }

                String input;
                while ((input = in.readLine()) != null) {
                    if (!input.matches("\\d{1,10}")) {
                        out.println("There was an exception on the server");
                        continue;
                    }

                    if (input.length() == 10 && input.compareTo("2147483647") > 0) {
                        out.println("There was an exception on the server");
                        continue;
                    }

                    int number = 0;
                    for (int j = 0; j < input.length(); j++) {
                        number = number * 10 + (input.charAt(j) - '0');
                    }

                    int factors = countFactors(number);
                    out.println("The number " + number + " has " + factors + " factors");
                }
                client.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
