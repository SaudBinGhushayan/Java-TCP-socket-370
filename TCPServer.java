import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;

public class TCPServer {

    public static void main(String[] args) throws IOException {

        Patient patientManager = new Patient(); // have allPatients array list which contain all patients
        Appointment appointmentsManager = new Appointment(); // have allAppointments array list which contain all
                                                             // appointments

        System.out.println("Establishing Clients ...");
        ServerSocket Server = new ServerSocket(0370);
        Socket ClientSocket = Server.accept();
        System.out.println("Connection Created ");
        BufferedReader inputfromClient = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(ClientSocket.getOutputStream(), true);
        Random random = new Random();

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
                    patientManager.insertPatient(new Patient(Integer.parseInt(sentence[0]), // patient_phone_number
                            (sentence[1]), // name
                            Integer.parseInt(sentence[2]), // age
                            sentence[3])); // gender
                } catch (Exception e) {
                }
            }

            // Transfer data from Appointments.csv to allAppointments
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

        // ==============================================================================================================================
        // //

        String choice;
        int PhoneNumber = Integer.parseInt(inputfromClient.readLine()); // Receive phone number from client as string
                                                                        // and convert to integer
        String isFound = "No";

        // Block of code to check if phone number in own DB
        {
            for (int i = 0; i < patientManager.getAllPatients().size(); i++)
                if (PhoneNumber == patientManager.getAllPatients().get(i).getPatient_Phone_number())
                    isFound = "Yes";
            outToClient.println(isFound); // send isFound to client
        }

        if (isFound.equalsIgnoreCase("Yes")) {
            for (int i = 0; i < patientManager.getAllPatients().size(); i++)
                if (PhoneNumber == patientManager.getAllPatients().get(i).getPatient_Phone_number())
                    outToClient.println(patientManager.getAllPatients().get(i).getName()); // send name of phone owner
                                                                                           // to client
        }

        String response = inputfromClient.readLine(); // response may have 3 value (Not want to create account, Want to
                                                      // create account, choice)

        // if client hasn't account
        {
            if (response.equalsIgnoreCase("Not want to create account")) {
                Server.close();
                System.exit(0);
            }

            if (response.equalsIgnoreCase("Want to create account")) {
                String col_2 = inputfromClient.readLine(); // receive name
                int col_3 = Integer.parseInt(inputfromClient.readLine()); // receive age
                String col_4 = inputfromClient.readLine(); // receive gender
                Patient p = new Patient(PhoneNumber, col_2, col_3, col_4);
                patientManager.insertPatient(p); // add p to allPatients array list
                response = inputfromClient.readLine(); // receive choice from client and assign it to response
            }
        }

        // ====================== Start Services ======================
        String first_entry = "yes";
        do {
            if (!first_entry.equalsIgnoreCase("yes"))
                choice = inputfromClient.readLine(); // if patient want another service
            else
                choice = response; // value of response here is choice (1,2,3,4,5)

            switch (choice) {
            case "1": // Display Appointment
                outToClient.println(appointmentsManager.dispalyAppointment(PhoneNumber)); // send to patient
                                                                                          // appointments of PhoneNumber
                first_entry = "no";
                break;

            case "2": // Reserve Appointment
                outToClient.println(appointmentsManager.displayDoctors()); // send to patient doctors
                outToClient.println("Enter year ->");
                String year = inputfromClient.readLine(); // Receive year form patient
                outToClient.println("Enter month ->");
                String month = inputfromClient.readLine(); // Receive month form patient
                outToClient.println("Enter day ->");
                String day = inputfromClient.readLine(); // Receive day form patient
                outToClient.println(appointmentsManager.reservedDoctors(year, month, day));
                outToClient.println("From ->");
                String hour = inputfromClient.readLine(); // Receive from form patient
                outToClient.println("To ->");
                String to = inputfromClient.readLine(); // Receive to form patient
                outToClient.println("How many hours do you want (Maximum 3 hours) ->");
                int no_hours = Integer.parseInt(inputfromClient.readLine()); // Receive number of hours form client
                outToClient.println(
                        "Enter the name of your doctor (e.g Dr.SaudBinGhushayan , Dr.AbdulmajeedDuraibi , Dr.KhalidAldayel)");
                String doc_name = inputfromClient.readLine(); // Receive which doctor patient want
                int newAppointmentNumber = random.nextInt(1000);
                Appointment appointment = new Appointment(newAppointmentNumber, PhoneNumber, doc_name, year, month, day,
                        hour, to, no_hours);
                outToClient.println((appointmentsManager.reserve(appointment))); // add
                System.out.println(appointmentsManager.getAllAppointments().size());
                outToClient.println("Appointment number: " + newAppointmentNumber + "@" + "with doctor: " + doc_name
                        + "@" + "At: " + hour + ":00 to " + to + ":00@" + "your price will be: "
                        + appointmentsManager.receipt(doc_name, no_hours));
                first_entry = "no";
                break;

            case "3": // Modify Appointment
                outToClient.println(appointmentsManager.dispalyAppointment(PhoneNumber)); // return info of appointment
                if (!(appointmentsManager.dispalyAppointment(PhoneNumber)
                        .equalsIgnoreCase("You don't have any appointment"))) {
                    outToClient.println("Which appointment wants to modify?@Choose appointment number :");
                    int numberOfAppointmentWantsToModify = Integer.parseInt(inputfromClient.readLine());
                    outToClient.println(
                            "Enter Doctor name (e.g Dr.SaudBinGhushayan , Dr.AbdulmajeedDuraibi , Dr.KhalidAldayel) ");
                    String doctor_name = inputfromClient.readLine();
                    outToClient.println("Enter year ->");
                    String year_ = inputfromClient.readLine();
                    outToClient.println("Enter month ->");
                    String month_ = inputfromClient.readLine();
                    outToClient.println("Enter day ->");
                    String day_ = inputfromClient.readLine();
                    outToClient.println("From ->");
                    String hour_ = inputfromClient.readLine();
                    outToClient.println("To ->");
                    String to_ = inputfromClient.readLine();
                    outToClient.println("How many hours do you want (Maximum 3 hours) ->");
                    int no_hours_ = Integer.parseInt(inputfromClient.readLine());
                    outToClient.println((appointmentsManager.modifyAppointment(numberOfAppointmentWantsToModify,
                            PhoneNumber, doctor_name, year_, month_, day_, hour_, to_, no_hours_)));
                }
                first_entry = "no";
                break;

            case "4": // Modify Appointment
                outToClient.println(appointmentsManager.dispalyAppointment(PhoneNumber)); // return info of appointment
                if (!(appointmentsManager.dispalyAppointment(PhoneNumber)
                        .equalsIgnoreCase("You don't have any appointment"))) {
                    System.out.println(appointmentsManager.getAllAppointments().size());
                    int numberOfAppointmentWantsToDelete = Integer.parseInt(inputfromClient.readLine());
                    outToClient.println(
                            appointmentsManager.deleteAppointment(numberOfAppointmentWantsToDelete, PhoneNumber));
                    System.out.println(appointmentsManager.getAllAppointments().size());
                }
                first_entry = "no";
                break;

            case "5": // Exit
                outToClient.println("Thanks, Goodbye :)");
                PrintWriter write_to_csv_patient = new PrintWriter(file1);
                PrintWriter write_to_csv_appointment = new PrintWriter(file2);
                int to_keep_header1 = 0;
                int to_keep_header2 = 0;
                for (int i = 0; i < patientManager.getAllPatients().size(); i++) {
                    if (to_keep_header1 == 0) {
                        write_to_csv_patient.println("Patient Phone Number,name ,age ,gender");
                    }
                    write_to_csv_patient.printf("%d,%s,%d,%s\n",
                            patientManager.getAllPatients().get(i).getPatient_Phone_number(),
                            patientManager.getAllPatients().get(i).getName(),
                            patientManager.getAllPatients().get(i).getAge(),
                            patientManager.getAllPatients().get(i).getGender());
                    to_keep_header1 = 1;

                }
                write_to_csv_patient.close();
                for (int i = 0; i < appointmentsManager.getAllAppointments().size(); i++) {
                    if (to_keep_header2 == 0) {
                        write_to_csv_appointment
                                .println("Appointment_No,Patint_Phone,Doctor_Name,year,month,day,hour,to,no_hours");
                    }
                    write_to_csv_appointment.printf("%d,%d,%s,%s,%s,%s,%s,%s,%s\n",
                            appointmentsManager.getAllAppointments().get(i).getAppointment_no(),
                            appointmentsManager.getAllAppointments().get(i).getPhoneNumber(),
                            appointmentsManager.getAllAppointments().get(i).getDoctor_Name(),
                            appointmentsManager.getAllAppointments().get(i).getYear(),
                            appointmentsManager.getAllAppointments().get(i).getMonth(),
                            appointmentsManager.getAllAppointments().get(i).getDay(),
                            appointmentsManager.getAllAppointments().get(i).getHour(),
                            appointmentsManager.getAllAppointments().get(i).getTo(),
                            appointmentsManager.getAllAppointments().get(i).getno_hours());
                    to_keep_header2 = 1;

                }
                write_to_csv_appointment.close();
                Server.close();
                System.exit(0);
            }
        } while (true);

    }

}
