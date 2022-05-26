import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void clientStart() {

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 20200);
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }

        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream()), true);
                Scanner scanner = new Scanner(System.in)) {

            String msg;
            while (true) {
                System.out.println("CALCULATE THE NTH VALUE F(N) FOR N= / example 0...92");
                msg = scanner.nextLine();
                out.println(msg);
                if ("end".equals(msg)) break;
                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }
    }


}

