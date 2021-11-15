import java.util.ArrayList;

public class Patient {

    private int Patient_Phone_number;
    private String name;
    private int age;
    private String gender;
    private ArrayList<Patient> allPatients = new ArrayList<Patient>();

    // Default Constructor
    public Patient() {

    }

    // Copy Constructor
    public Patient(int patient_Phone_number, String name, int age, String gender) {
        this.Patient_Phone_number = patient_Phone_number;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // insert new patient
    public void insertPatient(Patient p) {
        allPatients.add(p);
    }

    // ================= Getters and Setters ===================
    public ArrayList<Patient> getAllPatients() {
        return allPatients;
    }

    public int getPatient_Phone_number() {
        return Patient_Phone_number;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

}
