import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {
    public static ArrayList<Patients> allPatients = new ArrayList<Patients>();
    public static ArrayList<appointments> allAppointments = new ArrayList<appointments>();

    public static void main(String[] args) throws IOException {

        System.out.println("Establishing Clients ...");
        ServerSocket Server = new ServerSocket(0370);
        Socket ClientSocket = Server.accept();
        System.out.println("Connection Created ");

        BufferedReader inputfromClient = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(ClientSocket.getOutputStream(), true);

        String fileName = "Patient.csv";
        File file = new File(fileName);
        try {
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] sentence = data.split(",");
                try {
                    allPatients.add(new Patients(Integer.parseInt(sentence[0]), (sentence[1]),
                            Integer.parseInt(sentence[2]), sentence[3]));
                } catch (Exception e) {
                }
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int PhoneNumber = Integer.parseInt(inputfromClient.readLine());
        boolean flag = false;

        for (int i = 0; i < allPatients.size(); i++) {
            if (PhoneNumber == allPatients.get(i).getPatient_Phone_number()) {
                flag = true;
                break;
            } else {
                outToClient.println("You don't have an account do you want to create a new account (y/n)");
            }
        }
        if (flag == true) {
            // view previous and current appointment
            // reserve
        }

        // String name = inputfromClient.readLine();
        // outToClient.println("Display name " + name);
    }

}
