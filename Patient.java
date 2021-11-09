import java.util.ArrayList;

public class Patient {
    private int Patient_Phone_number;
    private String name;
    private int age;
    private String gender;
    private ArrayList<Patient> allPatients = new ArrayList<Patient>();

    public Patient() {

    }

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

    public ArrayList<Patient> getAllPatients() {
        return allPatients;
    }

    public void setAllPatients(ArrayList<Patient> allPatients) {
        this.allPatients = allPatients;
    }

    public int getPatient_Phone_number() {
        return Patient_Phone_number;
    }

    public void setPatient_Phone_number(int patient_Phone_number) {
        Patient_Phone_number = patient_Phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void displayAllPationInfo() {
        for (int i = 0; i < allPatients.size(); i++) {
            System.out.println(allPatients.get(i).Patient_Phone_number);
        }
    }
    /*
     * public String toString() { return "Patients [Patient_Phone_number=" +
     * Patient_Phone_number + ", name=" + name + ", age=" + age + ", gender=" +
     * gender + "]"; }
     */
}
