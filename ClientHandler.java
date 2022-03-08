import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader input;
    private PrintWriter output;
    static ScriptEngineManager mgr = new ScriptEngineManager();
    static ScriptEngine engine = mgr.getEngineByName("JavaScript");

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client =clientSocket;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run(){
        System.out.println("handling request");
        try{
            while (true){
                String request = input.readLine();
                if(!request.equals("")){
                    output.println("= " + engine.eval(request));
                }else{
                    System.out.println("You should type in an expression");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error");
        }finally {
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

     }

}
