import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Appointment {
	private int Appointment_no;
	private int PhoneNumber;
	private String Doctor_Name;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String to;
	private int no_hours;
	private int max;
	private ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();

	// Default Constructor
	public Appointment() {

	}

	// Copy Constructor
	public Appointment(int appointment_no, int phoneNumber, String doctor_Name, String year, String month, String day,
			String hour, String to, int no_hours) {
		Appointment_no = appointment_no;
		PhoneNumber = phoneNumber;
		Doctor_Name = doctor_Name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.to = to;
		this.no_hours = no_hours;
	}

	// Insert Appointment
	public void insertAppointment(Appointment A) {
		allAppointments.add(A);
	}

	// Count how many appointment for patient_Phone_number
	private int countAppointment(int patient_Phone_number) {
		int count = 0;
		for (int i = 0; i < allAppointments.size(); i++)
			if (patient_Phone_number == allAppointments.get(i).getPhoneNumber())
				count++;
		return count;
	}

	// Display Patient Appointment
	public String dispalyAppointment(int patient_Phone_number) {
		String appointmentDetails = "";
		char zero = '0';
		for (int i = 0; i < allAppointments.size(); i++) {
			if (patient_Phone_number == allAppointments.get(i).getPhoneNumber()) {
				if (allAppointments.get(i).getTo().length() == 1)
					allAppointments.get(i).setTo(zero + allAppointments.get(i).getTo());
				appointmentDetails += "Start printing information ...@";
				if (countAppointment(patient_Phone_number) == 0) {
					appointmentDetails = "You Don't have any appointments";
					return appointmentDetails;
				} else if (countAppointment(patient_Phone_number) == 1)
					appointmentDetails += "=====================================@You have "
							+ countAppointment(patient_Phone_number)
							+ " Appointment@=====================================";
				else
					appointmentDetails += "=====================================@You have "
							+ countAppointment(patient_Phone_number)
							+ " Appointments@====================================";

				appointmentDetails += "@Appointment Number: " + allAppointments.get(i).getAppointment_no() + "@Date: "
						+ allAppointments.get(i).getYear() + "/" + allAppointments.get(i).getMonth() + "/"
						+ allAppointments.get(i).getDay() + " At " + allAppointments.get(i).getHour() + ":00" + " to "
						+ allAppointments.get(i).getTo() + ":00@With " + allAppointments.get(i).getDoctor_Name()
						+ "@The price: " + allAppointments.get(i).receipt(allAppointments.get(i).getDoctor_Name(),
								allAppointments.get(i).getNo_hours());

				if (countAppointment(patient_Phone_number) == 1)
					return appointmentDetails + "@=====================================@End printing information";

				for (int j = 1; j < allAppointments.size(); j++) {
					if (patient_Phone_number == allAppointments.get(j).getPhoneNumber()) {
						if (allAppointments.get(j).getTo().length() == 1)
							allAppointments.get(j).setTo(zero + allAppointments.get(j).getTo());
						appointmentDetails += "@------------------------------------@Appointment Number: "
								+ allAppointments.get(j).getAppointment_no() + "@Date: "
								+ allAppointments.get(j).getYear() + "/" + allAppointments.get(j).getMonth() + "/"
								+ allAppointments.get(j).getDay() + " At " + allAppointments.get(j).getHour() + ":00 "
								+ "to " + allAppointments.get(j).getTo() + ":00@With "
								+ allAppointments.get(j).getDoctor_Name() + "@The price: "
								+ allAppointments.get(j).receipt(allAppointments.get(j).getDoctor_Name(),
										allAppointments.get(j).getNo_hours());
					}
				}
				return appointmentDetails + "@=====================================@End printing information";
			}

		}
		return "You don't have any appointment";
	}

	// Display doctors and their working hours and price
	public String displayDoctors() {
		String display = "Doctor Name :             Working hours              price per hour@"
				+ "Dr.Saud                          13:00 to 23:00             300 SR@"
				+ "Dr.Abdulmajeed                   16:00 to 23:00             250 SR@"
				+ "Dr.Khalid                     	18:00 to 22:00             200 SR@";
		return display;
	}

	// Display times that can't reserve on it
	public String reservedDoctors(String year, String month, String day) {

		for (int i = 0; i < allAppointments.size(); i++) {
			if (allAppointments.get(i).getYear().equals(year) && allAppointments.get(i).getMonth().equals(month)
					&& allAppointments.get(i).getDay().equals(day)) {
				return "Doctor name            reserved hours@" + allAppointments.get(i).getDoctor_Name()
						+ "         From " + allAppointments.get(i).getHour() + ":00 to "
						+ allAppointments.get(i).getTo() + ":00";

			}
		}

		return "There're no reserved appointments in this particular day , you can reserve any time";
	}

	// Insert new appointment but here
	public String reserve(Appointment appointment) {
		allAppointments.add(appointment);
		return "Appointment Reserved";

	}

	// Return price
	public int receipt(String doc_name, int no_hours) {
		int receipt = 0;
		switch (doc_name) {
		case "Dr.Saud":
			receipt = 300 * no_hours;
			break;
		case "Dr.Abdulmajeed":
			receipt = 250 * no_hours;
			break;
		case "Dr.Khalid":
			receipt = 200 * no_hours;
			break;
		}
		return receipt;
	}

	// Modify Appointment
	public String modifyAppointment(int appointment_no, int PhoneNumber, String doc_name, String year, String month,
			String day, String hour, String to, int no_hours) {
		ArrayList<Integer> Appointments = new ArrayList<>();

		for (int i = 0; i < allAppointments.size(); i++) {
			if (allAppointments.get(i).getPhoneNumber() == PhoneNumber) {
				Appointments.add(allAppointments.get(i).getAppointment_no());
			}
		}

		for (int i = 0; i < Appointments.size(); i++)
			if (Appointments.get(i) == appointment_no)
				for (int j = 0; j < allAppointments.size(); j++)
					if (allAppointments.get(j).getAppointment_no() == appointment_no) {
						allAppointments.get(j).setDoctor_Name(doc_name);
						allAppointments.get(j).setYear(year);
						allAppointments.get(j).setMonth(month);
						allAppointments.get(j).setDay(day);
						allAppointments.get(j).setHour(hour);
						allAppointments.get(j).setTo(to);
						allAppointments.get(j).setno_hours(no_hours);
						return "Appointment modified successfully";
					}

		return "The entered appointment number is not found";

	}

	// Delete Appointment
	public String deleteAppointment(int appointment_no, int PhoneNumber) {
		ArrayList<Integer> Appointments = new ArrayList<>();

		for (int i = 0; i < allAppointments.size(); i++) {
			if (allAppointments.get(i).getPhoneNumber() == PhoneNumber) {
				Appointments.add(allAppointments.get(i).getAppointment_no());
			}
		}

		for (int i = 0; i < Appointments.size(); i++)
			if (Appointments.get(i) == appointment_no)
				for (int j = 0; j < allAppointments.size(); j++)
					if (allAppointments.get(j).getAppointment_no() == appointment_no) {
						allAppointments.remove(j);
						return "Appointment Deleted";
					}

		return "The entered appointment number is not found";

	}

	// ================= Getters and Setters ===================

	public int getAppointment_no() {
		return Appointment_no;
	}

	public void setAppointment_no(int appointment_no) {
		Appointment_no = appointment_no;
	}

	public int getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getDoctor_Name() {
		return Doctor_Name;
	}

	public void setDoctor_Name(String doctor_Name) {
		Doctor_Name = doctor_Name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getno_hours() {
		return no_hours;
	}

	public void setno_hours(int no_hours) {
		this.no_hours = no_hours;
	}

	public int getNo_hours() {
		return no_hours;
	}

	public ArrayList<Appointment> getAllAppointments() {
		return allAppointments;
	}

}