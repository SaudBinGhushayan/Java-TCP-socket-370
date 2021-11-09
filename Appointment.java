import java.util.ArrayList;

public class Appointment {
	private int Appointment_no;
	private int PhoneNumber;
	private String Doctor_Name; // New attribute
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();

	public Appointment() {

	}

	public Appointment(int appointment_no, int phoneNumber, String doctor_Name, int year, int month, int day, int hour,
			int minute) {
		Appointment_no = appointment_no;
		PhoneNumber = phoneNumber;
		Doctor_Name = doctor_Name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
	}

	// insert new patient to array list
	public void insertAppointment(Appointment A) {
		allAppointments.add(A);
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public ArrayList<Appointment> getAllAppointments() {
		return allAppointments;
	}

	public void setAllAppointments(ArrayList<Appointment> allAppointments) {
		this.allAppointments = allAppointments;
	}

}