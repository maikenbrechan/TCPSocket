import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HTTP {
    final static int portNR = 8080;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(portNR);
        while (true) {
            System.out.println("Waiting for a connection request");
            Socket connection = server.accept();
            System.out.println("client connected" + connection);

            Scanner input = new Scanner(connection.getInputStream());
            PrintWriter output = new PrintWriter(connection.getOutputStream(), true);
            StringBuilder response = new StringBuilder();


                response.append("HTTP/1.1 200 OK\r\n\r\n");
                response.append("<!doctype html><html><body><H1>Welcome!</H1><UL>");

                while (input.hasNextLine()) {
                    String text = input.nextLine();
                    if (text.equals("")) {
                        System.out.println("\n DONE!!\n");
                        break;
                    }
                    System.out.println("Recieved input: " + text);
                    response.append("<LI>" + text + "</LI>");
                }
                response.append("</UL></body></html>");
            System.out.println("Get here!!!");
                output.println(response);
        }
    }

}
