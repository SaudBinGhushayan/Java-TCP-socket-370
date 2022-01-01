import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

	// === START HANDLING ERRORS === //

	static boolean checkPhoneNumber(String phone) {
		if (phone.length() != 9) // number of digits of phone number must 9 digits, other wise return false
			return false;

		if (phone.charAt(0) != 53) // first number must be 5, other wise return false
			return false;

		for (int i = 1; i < phone.length(); i++) // rest numbers must between 0 and 9, other wise return false
			if (phone.charAt(i) < 48 && phone.charAt(i) > 57)
				return false;

		return true; // true means phone number is valid
	}

	static boolean checkName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) < 97 || name.charAt(i) > 123) // all letter must be between a - z (small letters), other
																// wise return false
				return false;
		}
		return true; // true means name is valid
	}

	static boolean checkAge(String age) {
		if (age.length() != 1)
			if (age.length() != 2)
				return false; // if age more than 2 digits return false

		if (age.length() == 1) // if age one digit, this digit must between 1 and 9, other wise return false
			if (age.charAt(0) < 49 || age.charAt(0) > 57)
				return false;

		if (age.length() == 2) // if age two digits
		{
			if (age.charAt(0) < 49 || age.charAt(0) > 57) // first digit must between 1 and 9, other wise return false
				return false;

			if (age.charAt(1) < 48 || age.charAt(0) > 57) // second digit must between 0 and 9, other wise return false
				return false;
		}
		return true; // true means age is valid
	}

	static boolean checkYear(String year) {
		if (year.length() != 4) // number of digits must be 4, other wise return false
			return false;

		if (year.charAt(0) != 50) // first digit must be 2, other wise return false
			return false;

		if (year.charAt(1) != 48) // second digit must be 0, other wise return false
			return false;

		if (year.charAt(2) != 50) // third digit must be 2, other wise return false
			return false;

		if (year.charAt(3) > 50 || year.charAt(3) < 49) // forth digit must be 2 or 3, other wise return false
			return false;

		return true; // true means year is valid
	}

	static boolean checkMonth(String month) {

		if (month.length() != 1) // check number of digits, if digits of months not 1 or 2 digits return false
			if (month.length() != 2)
				return false; // if month more than 2 digits return false

		if (month.length() == 1) // if month one digit, this digit must between 1 and 12, other wise return false
			if (month.charAt(0) < 49 || month.charAt(0) > 57)
				return false;

		if (month.length() == 2) // if two digits, the first digit must be 1 and second digit must between 0 and
									// 2

			if (month.charAt(0) != 49 || month.charAt(1) < 48 || month.charAt(1) > 50)
				return false;

		return true; // true means month is valid
	}

	static boolean checkDay(String day) {

		if (day.length() != 1) // if day more than 2 digits return false
			if (day.length() != 2)
				return false;

		if (day.length() == 1) // if one digit, this digit must between 1 and 9
			if (day.charAt(0) < 49 || day.charAt(0) > 57)
				return false;

		if (day.length() == 2) // if two digits, the first must between 1 and 3 and second digit must between 0
								// and 9
			if (day.charAt(0) < 49 || day.charAt(0) > 51 || day.charAt(1) < 48 || day.charAt(1) > 57)
				return false;

		if (day.charAt(0) == 51) // if first digit 3 the second digit must 0 or 1
			if (day.charAt(1) != 48 && day.charAt(1) != 49)
				return false;

		return true; // true means day is valid
	}

	static boolean checkFrom(String from) {
		if (from.length() != 2) // must be digits, other wise return false
			return false;

		if (from.charAt(0) != 48 && from.charAt(0) != 49 && from.charAt(0) != 50) // first digit must between 0 and 2
			return false;

		if (from.charAt(0) == 48 || from.charAt(0) == 49) // if first digit 0 or 1, the second digit must between 0 and
															// 9
			if (from.charAt(1) < 48 || from.charAt(1) > 57)
				return false;

		if (from.charAt(0) == 50) // if first digit 2 the second digit must between 0 or 4
			if (from.charAt(1) < 48 || from.charAt(1) > 52)
				return false;

		return true; // true means from is valid
	}

	static boolean checkTo(String to) {
		if (to.length() != 2) // must be digits, other wise return false
			return false;
		if (to.charAt(0) != 48 && to.charAt(0) != 49 && to.charAt(0) != 50) // first digit must between 0 and 2
			return false;

		if (to.charAt(0) == 48 || to.charAt(0) == 49) // if first digit 0 or 1, the second digit must between 0 and 9
			if (to.charAt(1) < 48 || to.charAt(1) > 57)
				return false;

		if (to.charAt(0) == 50) // if first digit 2 the second digit must between 0 or 4
			if (to.charAt(1) < 48 || to.charAt(1) > 52)
				return false;

		return true; // true means to is valid
	}

	static boolean checkHours(String hours) // Hours maximum 3
	{
		if (hours.length() != 1) // must one digit
			return false;
		if (hours.charAt(0) < 49 || hours.charAt(0) > 51) // must 1 or 2 or 3
			return false;

		return true;
	}

	static boolean checkDrName(String name) {
		if (name.equalsIgnoreCase("Dr.Saud") || name.equalsIgnoreCase("Dr.Abdulmajeed") // doctor
																											// name must
																											// between
																											// these
																											// names
				|| name.equalsIgnoreCase("Dr.Khalid"))
			return true;
		return false;
	}

	// === END HANDLING ERRORS === //

	// ==============================================================================================================================
	// //

	// === START MAIN METHOD === //

	public static void main(String[] args) throws IOException {

		String year;
		String month;
		String day;
		String from;
		String to;
		String hours;
		String doctorName;
		String PhoneNumber;
		String choice;

		Socket Client = new Socket("localhost", 0370);
		System.out.println("Client Created");
		PrintWriter ToServer = new PrintWriter(Client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
		Scanner scan = new Scanner(System.in);

		do {
			System.out.println("Enter your phone number without 0: +966*********");
			PhoneNumber = scan.next(); // patient enter phone number
			if (!checkPhoneNumber(PhoneNumber))
				System.out.println("------------\nError, try again\n------------");
		} while (!checkPhoneNumber(PhoneNumber));
		ToServer.println(PhoneNumber); // send phone number as string to server

		String isFound = in.readLine(); // Receive if phone number in our DB or not

		if (isFound.equalsIgnoreCase("Yes")) // Have an account
		{
			System.out.print("=== Welcome Back ");
			System.out.println(in.readLine() + " ===");
		}

		else if (isFound.equalsIgnoreCase("No")) // Not have an account
		{
			System.out.println("You are not register, do you want to register? (N/Y)");
			do {
				choice = scan.next();
				if (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")))
					System.out.println("------------\nError, try again\n------------");
			} while (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")));

			if (choice.equalsIgnoreCase("N")) // Not have an account and don't want to register
			{
				System.out.println("Thanks, Goodbye :)");
				ToServer.println("Not want to create account");
				Client.close();
				System.exit(0);
			} else if (choice.equalsIgnoreCase("Y"))// Not have an account and want to register
			{
				ToServer.println("Want to create account");
				String name;
				do {
					System.out.println("Enter your name (all letters must be small) ");
					name = scan.next();
					if (!checkName(name))
						System.out.println("------------\nError, try again\n------------");
				} while (!checkName(name));

				String age;
				do {
					System.out.println("Enter your age ");
					age = scan.next();
					if (!checkAge(age))
						System.out.println("------------\nError, try again\n------------");
				} while (!checkAge(age));

				String gender;

				do {
					System.out.println("Enter your gender (M/F) ");
					gender = scan.next();
					if (!(gender.length() == 1 && (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))))
						System.out.println("------------\nError, try again\n------------");
				} while (!(gender.length() == 1 && (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F"))));

				// Start to send patient data to server
				ToServer.println(name);
				ToServer.println(Integer.parseInt(age));
				ToServer.println(gender);
				System.out.println("=== Account created ===");
			}

		}

		// ====================== Start Services ======================
		do {
			System.out.println("What do you want to do?" + "\n1- Display Appointment." + "\n2- Reserve Appointment."
					+ "\n3- Modify Appointment." + "\n4- Delete Appointment." + "\n5- Exit.");
			System.out.print("Enter a number from list -> ");
			choice = scan.next();
			if (!(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equals("3")
					|| choice.equals("4") || choice.equals("5")))
				System.out.println("------------\nError, enter number from 1 to 5\n------------");
			else {
				switch (choice) {

				case "1": // Display Appointment

					ToServer.println("1"); // send choice 1 to server
					System.out.println(in.readLine().replace("@", "\n")); // Appointments details
					break;

				case "2": // Reserve Appointment

					ToServer.println("2"); // send choice 2 to server
					System.out.print(in.readLine().replace("@", "\n"));// display doctors

					System.out.println(in.readLine());// enter year
					do {
						year = scan.next();
						if (!checkYear(year))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkYear(year));
					ToServer.println(year); // send year to server

					System.out.println(in.readLine());// enter month
					do {
						month = scan.next();
						if (!checkMonth(month))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkMonth(month));
					ToServer.println(month); // send year to server

					System.out.println(in.readLine());// enter day
					do {
						day = scan.next();
						if (!checkDay(day))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkDay(day));
					ToServer.println(day); // send day to server

					System.out.println(in.readLine().replace("@", "\n"));// display reserved doctors

					System.out.println(in.readLine());// enter from
					do {
						from = scan.next();
						if (!checkFrom(from))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkFrom(from));
					ToServer.println(from); // send from to server

					System.out.println(in.readLine());// enter to
					do {
						to = scan.next();
						if (!checkTo(to))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkTo(to));
					ToServer.println(to); // send to to server

					System.out.println(in.readLine());// enter number of hours
					do {
						hours = scan.next();
						if (!checkHours(hours))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkHours(hours));
					ToServer.println(hours); // send hours to server

					System.out.println(in.readLine());// enter doctor name

					do {
						doctorName = scan.next();
						if (!checkDrName(doctorName))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkDrName(doctorName));
					ToServer.println(doctorName); // send doctor name to server

					System.out.println(in.readLine());// reserving appointment
					System.out.println(in.readLine().replace("@", "\n"));// printing receipt
					break;

				case "3": // Modify Appointment

					ToServer.println("3"); // send choice 3 to server
					String serverMessage = in.readLine(); // previous appointments
					if (serverMessage.equalsIgnoreCase("You don't have any appointment")) {
						System.out.println(serverMessage);
					} else {
						System.out.println(serverMessage.replace("@", "\n"));// display previous appointments

						System.out.println(in.readLine().replace("@", "\n"));// display which appointment to modify

						String numberOfAppointmentWantsToModify;
						do {
							numberOfAppointmentWantsToModify = scan.next();
							if (numberOfAppointmentWantsToModify.length() > 3)
								System.out.println("------------\nError, try again\n------------");
						} while (!(numberOfAppointmentWantsToModify.length() <= 3));
						ToServer.println(numberOfAppointmentWantsToModify); // send number of appointment wants to
																			// modify server

						System.out.println(in.readLine());// enter doctor name
						do {
							doctorName = scan.next();
							if (!checkDrName(doctorName))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkDrName(doctorName));
						ToServer.println(doctorName); // send doctor name to server

						System.out.println(in.readLine());// enter year
						do {
							year = scan.next();
							if (!checkYear(year))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkYear(year));
						ToServer.println(year); // send year to server

						System.out.println(in.readLine());// enter month
						do {
							month = scan.next();
							if (!checkMonth(month))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkMonth(month));
						ToServer.println(month); // send year to month

						System.out.println(in.readLine());// enter day
						do {
							day = scan.next();
							if (!checkDay(day))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkDay(day));
						ToServer.println(day); // send day to server

						System.out.println(in.readLine());// enter from
						do {
							from = scan.next();
							if (!checkFrom(from))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkFrom(from));
						ToServer.println(from); // send from to server

						System.out.println(in.readLine());// enter to
						do {
							to = scan.next();
							if (!checkTo(to))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkTo(to));
						ToServer.println(to); // send to to server

						System.out.println(in.readLine());// enter number of hours
						do {
							hours = scan.next();
							if (!checkHours(hours))
								System.out.println("------------\nError, try again\n------------");
						} while (!checkHours(hours));
						ToServer.println(hours); // send hours to server

						System.out.println(in.readLine());
					}
					break;

				case "4": // Delete Appointment

					ToServer.println("4"); // send choice 4 to server
					String server_message = in.readLine(); // previous appointments
					if (server_message.equalsIgnoreCase("You don't have any appointment")) {
						System.out.println(server_message);
					} else {
						System.out.println(server_message.replace("@", "\n")); // display previous appointments
						System.out.println("Which appointment wants to delete?");
						System.out.println("Choose number from the list:");

						String numberOfAppointmentWantsToDelete;

						do {
							numberOfAppointmentWantsToDelete = scan.next();
							if (numberOfAppointmentWantsToDelete.length() > 3)
								System.out.println("------------\nError, try again\n------------");
						} while (!(numberOfAppointmentWantsToDelete.length() <= 3));
						ToServer.println(numberOfAppointmentWantsToDelete); // send number of appointment wants to
																			// delete server

						System.out.println(in.readLine()); // response message (confirmation or wrong entered number)

					}
					break;

				case "5": // Exit

					ToServer.println("5"); // send choice 5 to server
					System.out.println(in.readLine()); // Thanks, Goodbye :)
					Client.close();
					System.exit(0);
				}
			}
		} while (true);

	}

}
