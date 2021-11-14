import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {

	static String year;
	static String month;
	static String day;
	static String from;
	static String to;
	static String hours;
	static String doctorName;

	static boolean checkPhoneNumber(String phone) {
		if (phone.length() != 9) // if digits of phone number != 9 return false
			return false;

		if (phone.charAt(0) != 53) // if first number != 5 return false
			return false;

		for (int i = 1; i < phone.length(); i++) // if remaining number not between 0 and 9 return false
			if (phone.charAt(i) < 48 && phone.charAt(i) > 57)
				return false;

		return true;
	}

	static boolean checkName(String name) {
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) < 97 || name.charAt(i) > 123)
				return false;
		}
		return true;
	}

	static boolean checkAge(String age) {
		if (age.length() != 1)
			if (age.length() != 2)
				return false;

		if (age.length() == 1) // if one digit, this digit must between 1 and 9
			if (age.charAt(0) < 49 || age.charAt(0) > 57)
				return false;

		if (age.length() == 2)
			if (age.charAt(0) < 49 || age.charAt(0) > 57)
				if (age.charAt(1) < 49 || age.charAt(1) > 57)
					return false;

		return true;
	}

	static boolean checkYear(String year) {
		if (year.length() != 4) // if number of digits != 4
			return false;

		if (year.charAt(0) != 50) // if first digit != 2
			return false;

		if (year.charAt(1) != 48) // // if second digit != 0
			return false;

		if (year.charAt(2) != 50) // // if third digit != 2
			return false;

		if (year.charAt(3) > 50 || year.charAt(3) < 49) // // if forth digit > 2 and less than 1
			return false;

		return true;
	}

	static boolean checkMonth(String month) {

		if (month.length() != 1) // check number of digits, if digits of months not 1 or 2 digits return false
			if (month.length() != 2)
				return false;

		if (month.length() == 1) // if one digit, this digit must between 1 and 9
			if (month.charAt(0) < 49 || month.charAt(0) > 57)
				return false;

		if (month.length() == 2) // if two digits, this first must 1 and second digit must between 0 and 2

			if (month.charAt(0) != 49 || month.charAt(1) < 48 || month.charAt(1) > 50)
				return false;

		return true;
	}

	static boolean checkDay(String day) {

		if (day.length() != 1) // check number of digits, if digits of months not 1 or 2 digits return false
			if (day.length() != 2)
				return false;

		if (day.length() == 1) // if one digit, this digit must between 1 and 9
			if (day.charAt(0) < 49 || day.charAt(0) > 57)
				return false;

		if (day.length() == 2) // if two digits, this first must between 1 and 3 and second digit must between
								// 0 and 9

			if (day.charAt(0) < 49 || day.charAt(0) > 51 || day.charAt(1) < 49 || day.charAt(1) > 57) // if one digit
																										// this digit
																										// must between
																										// 1 and 9
				return false;

		if (day.charAt(0) == 51) // if first digit 3 the second digit must 0 or 1
			if (day.charAt(1) != 48 && day.charAt(1) != 49)
				return false;

		return true;
	}

	static boolean checkFrom(String from) {
		if (from.length() != 2)
			return false;

		if (from.charAt(0) != 48 && from.charAt(0) != 49 && from.charAt(0) != 50)
			return false;

		if (from.charAt(0) == 48 || from.charAt(0) == 49)
			if (from.charAt(1) < 48 || from.charAt(1) > 57)
				return false;

		if (from.charAt(0) == 50) // if first digit 3 the second digit must 0 or 1
			if (from.charAt(1) < 48 || from.charAt(1) > 52)
				return false;

		return true;
	}

	static boolean checkTo(String to) {
		if (to.length() != 2)
			return false;
		if (to.charAt(0) != 48 && to.charAt(0) != 49 && to.charAt(0) != 50)
			return false;

		if (to.charAt(0) == 48 || to.charAt(0) == 49)
			if (to.charAt(1) < 48 || to.charAt(1) > 57)
				return false;

		if (to.charAt(0) == 50) // if first digit 3 the second digit must 0 or 1
			if (to.charAt(1) < 48 || to.charAt(1) > 52)
				return false;

		return true;
	}

	static boolean checkHours(String hours) // Hours maximum 4
	{
		if (hours.length() != 1) // must one digit
			return false;
		if (hours.charAt(0) < 49 || hours.charAt(0) > 52) // must 1 or 2 or 3
			return false;

		return true;
	}

	static boolean checkDrName(String name) {
		if (name.equalsIgnoreCase("Dr.SaudBinGhushayan") || name.equalsIgnoreCase("Dr.AbdulmajeedDuraibi")
				|| name.equalsIgnoreCase("Dr.KhalidAldayel"))
			return true;
		return false;

	}

	static boolean checkAppointmentNumber(String appointmnetNumber) {
		if (appointmnetNumber.length() < 1 && appointmnetNumber.length() > 3)
			return true;
		else
			return false;

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
			if (!checkPhoneNumber(PhoneNumber))
				System.out.println("------------\nError, try again\n------------");
		} while (!checkPhoneNumber(PhoneNumber));

		ToServer.println(PhoneNumber);

		String isFound = in.readLine();

		String choice;

		if (isFound.equalsIgnoreCase("Yes")) {
			System.out.print("=== Welcome Back ");
			System.out.println(in.readLine() + " ===");
		}

		else if (isFound.equalsIgnoreCase("No")) {
			System.out.println("You are not register, do you want to register? (N/Y)");
			do {
				choice = scan.next();
				if (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")))
					System.out.println("------------\nError, try again\n------------");
			} while (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")));

			if (choice.equalsIgnoreCase("N")) {
				System.out.println("Thanks, Goodbye :)");
				System.exit(0);
			} else if (choice.equalsIgnoreCase("Y"))// not register and want to create account
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

				// start to send client data to server
				ToServer.println(name);
				ToServer.println(Integer.parseInt(age));
				ToServer.println(gender);
				System.out.println("=== Account created ===");
			} else {
				System.out.println("Sorry Wrong Entry goodbye ");
				System.exit(0);
			}

		}

		choice = "0";

		do {
			// ==============Start Services===================
			System.out.println("What do you want to do?" + "\n1- Display Appointment." + "\n2- Reserve Appointment."
					+ "\n3- Modify Appointment." + "\n4- Delete Appointment." + "\n5- Exit.");
			System.out.print("Enter a number from list -> ");
			choice = scan.next();
			if (!(choice.equalsIgnoreCase("1") || choice.equalsIgnoreCase("2") || choice.equals("3")
					|| choice.equals("4") || choice.equals("5")))
				System.out.println("------------\nError, enter number from 1 to 5\n------------");
			else {
				switch (choice) {

				case "1": {
					System.out.println(choice);
					ToServer.println("1");
					System.out.println(in.readLine().replace("@", "\n"));
					break;
				}

				case "2": {
					ToServer.println("2");
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
					ToServer.println(month); // send year to month

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
					ToServer.println(hours); // send hours as integer to server

					System.out.println(in.readLine());// enter doc name

					do {
						doctorName = scan.next();
						if (!checkDrName(doctorName))
							System.out.println("------------\nError, try again\n------------");
					} while (!checkDrName(doctorName));
					ToServer.println(doctorName); // send doctor name to server

					System.out.println(in.readLine());// reserving appointment
					System.out.println(in.readLine().replace("@", "\n"));// printing receipt
					break;
				}

				case "3": {
					ToServer.println("3");
					String serverMessage = in.readLine();
					if (serverMessage.equalsIgnoreCase("You don't have any appointment")) {
						System.out.println(serverMessage);
					} else {
						System.out.println(serverMessage.replace("@", "\n"));// display appointments

						System.out.println(in.readLine().replace("@", "\n"));// display which appointment to modify

						String numberOfAppointmentWantsToModify;
						do {
							numberOfAppointmentWantsToModify = scan.next();
							if (numberOfAppointmentWantsToModify.length() > 3)
								System.out.println("------------\nError, try again\n------------");
						} while (!(numberOfAppointmentWantsToModify.length() <= 3));
						ToServer.println(numberOfAppointmentWantsToModify);

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
				}
				case "4": {
					ToServer.println("4");
					String serverMessage = in.readLine();
					if (serverMessage.equalsIgnoreCase("You don't have any appointment")) {
						System.out.println(serverMessage);
					} else {
						System.out.println(serverMessage.replace("@", "\n"));
						System.out.println("Which appointment wants to delete?");
						System.out.println("Choose number from the list:");

						String numberOfAppointmentWantsToDelete;

						do {
							numberOfAppointmentWantsToDelete = scan.next();
							if (numberOfAppointmentWantsToDelete.length() > 3)
								System.out.println("------------\nError, try again\n------------");
						} while (!(numberOfAppointmentWantsToDelete.length() <= 3));
						ToServer.println(numberOfAppointmentWantsToDelete);

						System.out.println(in.readLine());

					}
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
