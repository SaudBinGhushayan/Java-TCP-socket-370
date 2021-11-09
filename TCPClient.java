import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    static void services() {
        System.out.println("========================Welcome to MME Mental Hostpital========================");
        System.out.println("Select services : \n==============" + "1) View all appointments \n=============="
                + "2) Reserve an appointment \n==============" + "3) Modify appointment \n=============="
                + "4) Delete appointment\n==============" + "5) Exit ");

    }

    public static void main(String[] args) throws IOException {
        Socket Client = new Socket("localhost", 0370);
        System.out.println("Client Created");
        BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter ToServer = new PrintWriter(Client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(Client.getInputStream()));

        String server_message = "";
        System.out.println("Enter your phone number ");
        int PhoneNumber = Integer.parseInt(ClientInput.readLine());
        ToServer.println(PhoneNumber);

        String found = in.readLine();
        System.out.println(found);
        // server_message = in.readLine();
        while (true) {
            if (found.equalsIgnoreCase("f")) {
                System.out.println(in.readLine());
                String choice = ClientInput.readLine();
                ToServer.println(choice);

                if (choice.equalsIgnoreCase("y")) {
                    System.out.println(in.readLine());
                    ToServer.println(ClientInput.readLine());
                    System.out.println(in.readLine());
                    ToServer.println(Integer.parseInt(ClientInput.readLine()));
                    System.out.println(in.readLine());
                    ToServer.println(ClientInput.readLine());
                } else
                    System.out.println(in.readLine());
            } else {
                services();
                int menu_choice = Integer.parseInt(ClientInput.readLine());
                ToServer.println(menu_choice);
                System.out.println(in.readLine());

                // if (server_message.equals("Thanks, Goodbye :)")) {
                // System.out.println(server_message);
                // break;
                // }
            }
        }

        // System.out.println(in.readLine());

    }

}
