public class appointments {
    private int Appointment_no;
    private int PhoneNumber;
    private int year;
    private int month;
    private int day;
    private int hours;

    public appointments(int appointment_no, int phoneNumber, int year, int month, int day, int hours) {
        this.Appointment_no = appointment_no;
        this.PhoneNumber = phoneNumber;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
