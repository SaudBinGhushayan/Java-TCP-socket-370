import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

    static boolean checkPhoneNumber(String phone) {
        if (phone.charAt(0) != 5)
            return false;
        for (int i = 1; i < phone.length(); i++) {
            if (phone.charAt(i) <= 48 && phone.charAt(i) >= 57)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Socket Client = new Socket("localhost", 0370);
        System.out.println("Client Created");
        BufferedReader ClientInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter ToServer = new PrintWriter(Client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
        Scanner scan = new Scanner(System.in);

        String PhoneNumber;
        do {
            System.out.println("Enter your phone number without 0: +966*********");
            PhoneNumber = scan.next();
            if (PhoneNumber.length() != 9)
                System.out.println("------------\nError, try again\n------------");
        } while (PhoneNumber.length() != 9);

        ToServer.println(PhoneNumber);

        String isFound = in.readLine();

        String choice;

        if (isFound.equalsIgnoreCase("Yes"))
            System.out.println("You already have account");

        else if (isFound.equalsIgnoreCase("No")) {
            System.out.println("You are not register, do you want to register? (N/Y)");
            choice = scan.next();
            if (choice.equalsIgnoreCase("N")) {
                System.out.println("Thanks, Goodbye :)");
                System.exit(0);
            } else if (choice.equalsIgnoreCase("Y"))// not register and want to create account
            {
                ToServer.println("Want to create account");
                System.out.println("Enter your name ");
                String name = scan.next();
                System.out.println("Enter your age ");
                int age = scan.nextInt();
                System.out.println("Enter your gender (M/F) ");
                String gender = scan.next();
                // start to send client data to server
                ToServer.println(name);
                ToServer.println(age);
                ToServer.println(gender);
                System.out.println("Account created");
            } else {
                System.out.println("Sorry Wrong Entry goodbye ");
                System.exit(0);
            }

        }

        choice = "0";

        do {
            // ==============Start Services===================
            System.out.println("What do you want to do?" + "\n1- Display Appointment." + "\n2- Reserve Appointment"
                    + "\n3- Modify Appointment." + "\n4- Delete Appointment." + "\n5- Exit.");
            System.out.print("Enter a number from list -> ");
            choice = scan.next();
            if (!(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equals("3")
                    || choice.equals("4") || choice.equals("5")))
                System.out.println("------------\nError, enter number from 1 to 5\n------------");
            else {

                switch (choice) {

                case "1": {
                    ToServer.println("1");
                    System.out.println(in.readLine().replace("@", "\n"));
                    break;
                }

                case "2": {
                    ToServer.println("2");
                    System.out.println(in.readLine().replace("@", "\n"));
                    System.out.println(in.readLine());
                    ToServer.println(scan.next());
                    System.out.println(in.readLine());
                    ToServer.println(scan.next());
                    System.out.println(in.readLine());
                    ToServer.println(scan.next());
                    System.out.println(in.readLine().replace("@", "\n"));
                    break;
                }

                case "3": {
                    // some code
                    break;
                }

                case "4": {
                    // some code
                    break;
                }

                case "5": {
                    ToServer.println("5");
                    System.out.println(in.readLine());
                    Client.close();
                    System.exit(0);
                }

                }
            }
        } while (true);

    }

}
