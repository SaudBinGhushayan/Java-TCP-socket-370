import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Scanner;

public class TCPServer {

    public static void main(String[] args) throws IOException {

        Patient pationtsManager = new Patient();
        Appointment appointmentsManager = new Appointment();

        System.out.println("Establishing Clients ...");
        ServerSocket Server = new ServerSocket(0370);
        Socket ClientSocket = Server.accept();
        System.out.println("Connection Created ");
        BufferedReader inputfromClient = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(ClientSocket.getOutputStream(), true);

        // Start Transfer Data from DB to system
        String PatientFile = "Patient.csv";
        File file1 = new File(PatientFile);
        String AppointmentsFile = "Appointments.csv";
        File file2 = new File(AppointmentsFile);
        try {
            // Transfer data from patient.csv to allPatients
            Scanner inputStream1 = new Scanner(file1);
            while (inputStream1.hasNext()) {
                String data = inputStream1.next();
                String[] sentence = data.split(",");
                try {
                    pationtsManager.insertPatient(new Patient(Integer.parseInt(sentence[0]), // patient_phone_number
                            (sentence[1]), // name
                            Integer.parseInt(sentence[2]), // age
                            sentence[3])); // gender
                } catch (Exception e) {
                }
            }

            // Transfer data from patient.csv to allAppointments
            Scanner inputStream2 = new Scanner(file2);
            while (inputStream2.hasNext()) {
                String data = inputStream2.next();
                String[] sentence = data.split(",");
                try {
                    appointmentsManager.insertAppointment(new Appointment(Integer.parseInt(sentence[0]), // Appointment_Number
                            Integer.parseInt(sentence[1]), // Patient_phone number
                            (sentence[2]), // Doctor name
                            (sentence[3]), // Year
                            (sentence[4]), // Month
                            (sentence[5]), // Day
                            (sentence[6]), // Hour
                            (sentence[7]), // to
                            Integer.parseInt(sentence[8]))); // Number of hours
                } catch (Exception e) {
                }
            }

            inputStream1.close();
            inputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(pationtsManager.getAllPatients().size());
        String choice = "";

        int PhoneNumber = Integer.parseInt(inputfromClient.readLine()); // Receive phone number from client
        String isFound = "No";

        {// This Block of code to check if phone number is in own DB
            for (int i = 0; i < pationtsManager.getAllPatients().size(); i++)
                if (PhoneNumber == pationtsManager.getAllPatients().get(i).getPatient_Phone_number())
                    isFound = "Yes";
            outToClient.println(isFound);
        }

        // appointmentsManager.dispalyAppointment(PhoneNumber, outToClient);

        // is client have account OR want to create account
        String response = inputfromClient.readLine();
        if (response.equalsIgnoreCase("Want to create account")) {
            String col_2 = inputfromClient.readLine();
            int col_3 = Integer.parseInt(inputfromClient.readLine());
            String col_4 = inputfromClient.readLine();
            Patient p = new Patient(PhoneNumber, col_2, col_3, col_4);
            pationtsManager.insertPatient(p);
        }

        System.out.println(pationtsManager.getAllPatients().size());

        if (!response.equalsIgnoreCase("Want to create account"))
            ;
        String first_entry = "yes";
        do {
            if (!first_entry.equals("yes")) {
                choice = inputfromClient.readLine();
                System.out.println("f" + choice);
            } else {
                choice = response;
                System.out.println("b" + choice);
            }

            switch (choice) {
            case "1":
                outToClient.println(appointmentsManager.dispalyAppointment(PhoneNumber, outToClient));
                first_entry = "no";
                break;
            case "2":
                outToClient.println(appointmentsManager.displayDoctors(outToClient));
                outToClient.println("Enter year");
                String year = inputfromClient.readLine();
                outToClient.println("Enter month");
                String month = inputfromClient.readLine();
                outToClient.println("Enter day");
                String day = inputfromClient.readLine();
                outToClient.println(appointmentsManager.reservedDoctors(year, month, day, outToClient));
                first_entry = "no";
                break;
            case "3":
                outToClient.println(("modify"));
                first_entry = "no";
                break;
            case "4":
                outToClient.println("delete");
                first_entry = "no";
                break;
            case "5":
                outToClient.println("Thanks, Goodbye :)");
                Server.close();
                System.exit(0);
            }
        } while (true);

    }

}
