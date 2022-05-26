import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class Server {

    public void serverStart() {

        System.out.println("Fibonacci Numbers Calculator");

        ServerSocket servSocket = null;
        try {
            servSocket = new ServerSocket(20200);
        } catch (IOException exc) {
            exc.printStackTrace(System.out);
        }

        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                String line;
                while (((line = in.readLine()) != null) && (!line.equals("end"))) {
                    int num = Integer.parseInt(line);
                    if (num == 0) {
                        out.println("F(0) = 0");
                    } else {
                        long fibonacci = getFibonacci(num);
                        out.println("F(" + line + ") = " + fibonacci);
                    }
                }
            } catch (IOException exc) {
                exc.printStackTrace(System.out);
            }
        }

    }

    public static long getFibonacci(int num) {
        return Stream.iterate(new long[]{1, 1}, arr -> new long[]{arr[1], arr[0] + arr[1]})
                .limit(num)
                .map(y -> y[0])
                .reduce((a, b) -> b).get();
    }


}

// использовал Blocking тк нужен конечный результат
//Blocking - поток блокируется до тех пор,
// пока не произойдет чтение из потока или запись в поток.
