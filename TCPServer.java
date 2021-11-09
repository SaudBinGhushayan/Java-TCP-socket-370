import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer {

    static ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();

    static int countAppointment(int patient_Phone_number) {
        int count = 0;
        return count;
    }

    // Display Patient Appointment
    static String dispalyAppointment(int patient_Phone_number) {
        String s = "";
        for (int i = 0; i < allAppointments.size(); i++) {
            if (patient_Phone_number == allAppointments.get(i).getPhoneNumber()) {
                s += "You have " + countAppointment(patient_Phone_number) + " Appointments Appointment Information "
                        + " Appointment Number: " + allAppointments.get(i).getAppointment_no() + " Doctor: "
                        + allAppointments.get(i).getDoctor_Name() + "Appointment in : "
                        + allAppointments.get(i).getYear() + " / " + allAppointments.get(i).getMonth() + " / "
                        + allAppointments.get(i).getDay() + " At: " + allAppointments.get(i).getHour() + " : "
                        + allAppointments.get(i).getMinute() + "";

            }
            if (s.equals("")) {
                return "Sorry, There're no appointments for you :(";
            }
        }
        return s;
    }

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
                            Integer.parseInt(sentence[3]), // Year
                            Integer.parseInt(sentence[4]), // Month
                            Integer.parseInt(sentence[5]), // Day
                            Integer.parseInt(sentence[6]), // Hour
                            Integer.parseInt(sentence[7]))); // minute
                } catch (Exception e) {
                }
            }

            inputStream1.close();
            inputStream2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int PhoneNumber = Integer.parseInt(inputfromClient.readLine()); // Receive phone number from client
        // Start Services
        while (true) {
            String found = "f";
            String choice = "";
            int menu_choice = 0;
            for (int i = 0; i < pationtsManager.getAllPatients().size(); i++) {
                if (PhoneNumber == pationtsManager.getAllPatients().get(i).getPatient_Phone_number()) {
                    found = "t";
                    outToClient.println(found);
                    break;
                }
            }
            outToClient.print(found);
            if (found.equalsIgnoreCase("f")) {
                outToClient.println("You don't have an account, do you want to create a new account (Y/N)");
                choice = inputfromClient.readLine();
                if (choice.equalsIgnoreCase("y")) {
                    int col_1 = PhoneNumber;
                    outToClient.println("Enter your name ");
                    String col_2 = inputfromClient.readLine();
                    outToClient.println("Enter your age ");
                    int col_3 = Integer.parseInt(inputfromClient.readLine());
                    outToClient.println("Enter your Gender (M/F) ");
                    String col_4 = inputfromClient.readLine();
                    Patient p = new Patient(col_1, col_2, col_3, col_4);
                    pationtsManager.insertPatient(p);

                } else // if we arrive here that's mean client doesn't have an account and not willing
                       // to register
                {
                    outToClient.println("Thanks, Goodbye :)");
                    break;
                }
            } else {
                menu_choice = Integer.parseInt(inputfromClient.readLine());
                switch (menu_choice) {
                case 1:
                    outToClient.println(dispalyAppointment(PhoneNumber));
                    break;
                case 2:
                    outToClient.println("reserve");
                    break;
                case 3:
                    outToClient.println(("modify"));
                    break;
                case 4:
                    outToClient.println("delete");
                    break;
                case 5:
                    outToClient.println("Thanks, Goodbye :)");
                    break;
                default:
                    outToClient.println("Invalid entry try again");

                }
            }

        }
        Server.close();

        // if we arrive here that's mean patient have account in own DB

        // View all appointments for patient

        // Do you want to reserve
        // Do you want to modify
        // Do you want to delete
        // Exit(-1)
    }
}