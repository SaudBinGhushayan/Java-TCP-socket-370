import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket Client = new Socket("localhost", 0370);
        System.out.println("Client Created");

        BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your phone number ");
        String input = ClientInput.readLine();
        PrintWriter ToServer = new PrintWriter(Client.getOutputStream(), true);
        ToServer.println(input);
        BufferedReader in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
        System.out.println(in.readLine());

    }
}
