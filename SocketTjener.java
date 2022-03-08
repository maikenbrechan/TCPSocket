import java.io.IOException;
import java.net.*;

public class SocketTjener {
    public static void main(String[] args) throws IOException{
        final int portNR = 1970;

        ServerSocket tjener = new ServerSocket(portNR);
        while (true) {
            Socket connection = tjener.accept();
            
            ClientHandler clientThread = new ClientHandler(connection);
            Thread thread = new Thread(clientThread);
            thread.start();
        }
    }
}
