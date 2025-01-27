/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce190770_v04;

/**
 * V04 - Doctor Management Program This class define structure of Doctor object
 *
 * @author Dinh Cong Phuc - CE190770 - 27/Jan/2025
 */
public class Doctor {

    // Declares a private string variable to store doctor's code
    private String Code;
    // Declares a private string variable to store doctor's name
    private String Name;
    // Declares a private string variable to store doctor's specialization
    private String Specialization;
    // Declares a private integer variable to store doctor's availability
    private int Availability;

    // Constructor that takes 4 parameters to create a new Doctor object
    public Doctor(String Code, String Name, String Specialization, int Availability) {
        // Assigns the passed Code parameter to the class Code variable
        this.Code = Code;
        // Assigns the passed Name parameter to the class Name variable
        this.Name = Name;
        // Assigns the passed Specialization parameter to the class Specialization variable
        this.Specialization = Specialization;
        // Assigns the passed Availability parameter to the class Availability variable
        this.Availability = Availability;
    }

    // Method to get the doctor's code
    public String getCode() {
        // Returns the Code value
        return Code;
    }

    // Method to get the doctor's name
    public String getName() {
        // Returns the Name value
        return Name;
    }

    // Method to get the doctor's specialization
    public String getSpecialization() {
        // Returns the Specialization value
        return Specialization;
    }

    // Method to get the doctor's availability
    public int getAvailability() {
        // Returns the Availability value
        return Availability;
    }

    // Note: no setter for Code as it shouldn't change after creation
    // Method to set a new name for the doctor
    public void setName(String Name) {
        // Updates the Name value with the new passed parameter
        this.Name = Name;
    }

    // Method to set a new specialization for the doctor
    public void setSpecialization(String Specialization) {
        // Updates the Specialization value with the new passed parameter
        this.Specialization = Specialization;
    }

    // Method to set a new availability for the doctor
    public void setAvailability(int Availability) {
        // Updates the Availability value with the new passed parameter
        this.Availability = Availability;
    }
}
