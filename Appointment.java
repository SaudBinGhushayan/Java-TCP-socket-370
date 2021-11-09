import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Appointment {
	private int Appointment_no;
	private int PhoneNumber;
	private String Doctor_Name; // New attribute
	private String year;
	private String month;
	private String day;
	private String hour;
	private String to;
	private int no_hours;
	private ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();

	public Appointment() {

	}

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

	// insert new patient to array list
	public void insertAppointment(Appointment A) {
		allAppointments.add(A);
	}

	private int countAppointment(int patient_Phone_number) {
		int count = 0;
		for (int i = 0; i < allAppointments.size(); i++)
			if (patient_Phone_number == allAppointments.get(i).getPhoneNumber())
				count++;
		return count;
	}

	// Display Patient Appointment
	public String dispalyAppointment(int patient_Phone_number, PrintWriter outToClient) {
		String appointmentDetails = "";
		char zero = '0';
		for (int i = 0; i < allAppointments.size(); i++)
			if (patient_Phone_number == allAppointments.get(i).getPhoneNumber()) {
				if (allAppointments.get(i).getTo().length() == 1)
					allAppointments.get(i).setTo(zero + allAppointments.get(i).getTo());
				appointmentDetails += "Starting printing information ...@You have "
						+ countAppointment(patient_Phone_number) + " Appointments@Appointment Number:"
						+ allAppointments.get(i).getAppointment_no() + "@Date: " + allAppointments.get(i).getYear()
						+ "/" + allAppointments.get(i).getMonth() + "/" + allAppointments.get(i).getDay() + " At "
						+ allAppointments.get(i).getHour() + ":" + allAppointments.get(i).getTo() + "@With Dr."
						+ allAppointments.get(i).getDoctor_Name();
				for (int j = 1; j < countAppointment(patient_Phone_number); j++) {
					if (allAppointments.get(j).getTo().length() == 1)
						allAppointments.get(j).setTo(zero + allAppointments.get(j).getTo());
					appointmentDetails += "@------------------------------------@Appointment Number: "
							+ allAppointments.get(j).getAppointment_no() + "@Date: " + allAppointments.get(j).getYear()
							+ "/" + allAppointments.get(j).getMonth() + "/" + allAppointments.get(j).getDay() + " At "
							+ allAppointments.get(j).getHour() + ":" + allAppointments.get(j).getTo() + "@With Dr."
							+ allAppointments.get(j).getDoctor_Name();
				}
				return appointmentDetails;
			}

		return "You don't have any appointment!";
	}

	public String displayDoctors(PrintWriter OutToClient) {
		String display = "Doctor Name :             Working hours             price per hour@"
				+ "Dr.Saud BinGhushayan             13:00 to 23:00             300 SR@"
				+ "Dr.Abdulmajeed Duraibi           16:00 to 23:00             250 SR@"
				+ "Dr.Khalid Aldayel             	18:00 to 22:00             200 SR@";
		return display;
	}

	public String reservedDoctors(String year, String month, String day, PrintWriter OutToClient) {
		String display = "";
		for (int i = 0; i < allAppointments.size(); i++) {
			if (allAppointments.get(i).getYear() == year && allAppointments.get(i).getMonth() == month
					&& allAppointments.get(i).getDay() == day) {
				display = "Doctor name            reserved hours@" + allAppointments.get(i).getDoctor_Name()
						+ "         From " + allAppointments.get(i).getHour() + ":00 to "
						+ allAppointments.get(i).getTo() + ":00";
				return display;
			}
		}

		return "There'are no reserved appointments in this particular day";
	}

	public ArrayList<Appointment> reserve(int Phone_number, String doc_name, String year, String month, String day,
			String hour, String to, int no_hours, ArrayList<Appointment> allAppointments) {
		boolean found = false;
		for (int i = 0; i < allAppointments.size(); i++)
			if (allAppointments.get(i).getDoctor_Name() != doc_name && allAppointments.get(i).getYear() != year
					&& allAppointments.get(i).getMonth() != month && allAppointments.get(i).getDay() != day
					&& allAppointments.get(i).getHour() != hour && allAppointments.get(i).getTo() != to)
				found = true;
		if (found)
			// we must print to the client that there's an appointment with his entry
			System.out.println();
		else
			allAppointments.add(allAppointments.size() + 1, new Appointment(allAppointments.size() + 1, Phone_number,
					doc_name, year, month, day, hour, to, no_hours));
		return allAppointments;

	}

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

	public void setTo(String minute) {
		this.to = to;
	}

	public int getno_hours() {
		return no_hours;
	}

	public void setno_hours(int minute) {
		this.no_hours = no_hours;
	}

	public ArrayList<Appointment> getAllAppointments() {
		return allAppointments;
	}

	public void setAllAppointments(ArrayList<Appointment> allAppointments) {
		this.allAppointments = allAppointments;
	}

}