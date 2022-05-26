
public class Main {
    public static void main(String[] args) {

        Server server = new Server();
        Client client = new Client();

        Thread serverThread = new Thread(null, server::serverStart, "Server");
        Thread clientThread = new Thread(null, client::clientStart, "Client");

        serverThread.start();
        clientThread.start();

    }
}
