// + serve(int): void ---> UML diagram

public class Server {
    private ServerSocket serverSocket;
    private List<LocalDateTime> connectedTimes = new ArrayList<>();

    public Server (int port) {

    }

    public void serve(int numClients) {

    }

    public List<LocalDateTime> getConnectedTimes() {
        return connectedTimes;
    }
}