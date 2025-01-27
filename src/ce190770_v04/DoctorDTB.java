/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce190770_v04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * V04 - Doctor Management Program This class act as a database to store all
 * doctor information and do operation on data
 *
 * @author Dinh Cong Phuc - CE190770 - 27/Jan/2025
 */
public class DoctorDTB {

    // Creates a HashMap to store doctors with their codes as keys
    HashMap<String, Doctor> drList = new HashMap<String, Doctor>();

    // Method to add a new doctor to the database
    public void addDr(String code, String name, String spec, int avail) {
        // Creates a new Doctor object with the provided parameters
        Doctor doctor = new Doctor(code, name, spec, avail);
        // Adds the doctor to the HashMap with code as key
        drList.put(code, doctor);
    }

    // Method to update an existing doctor's information
    public void updateDr(String code, String name, String spec, int avail) {
        // Gets the doctor object from the HashMap using the code
        Doctor doctor = drList.get(code);
        // Updates the doctor's name
        doctor.setName(name);
        // Updates the doctor's specialization
        doctor.setSpecialization(spec);
        // Updates the doctor's availability
        doctor.setAvailability(avail);
    }

    // Method to delete a doctor from the database
    public void deleteDr(String code) {
        // Removes the doctor from HashMap using their code
        drList.remove(code);
    }

    // Method to search for doctors based on input criteria
    public List<Doctor> searchDr(String input) {
        // Converts input to uppercase and removes leading/trailing spaces
        String normalizedInput = input.toUpperCase().trim();
        // Creates a list to store matching doctors
        List<Doctor> output = new ArrayList<>();

        // If input is "*", add all doctors to output
        if (normalizedInput.equals("*")) {
            output.addAll(drList.values());
        } else {
            // Iterate through all doctors in the database
            for (Map.Entry<String, Doctor> entry : drList.entrySet()) {
                Doctor dr = entry.getValue();
                // Check if any field matches the search input
                if (matchesInput(entry.getKey(), normalizedInput)
                        || matchesInput(dr.getName(), normalizedInput)
                        || matchesInput(dr.getSpecialization(), normalizedInput)
                        || matchesAvailability(dr.getAvailability(), normalizedInput)) {
                    // Add matching doctor to output list
                    output.add(dr);
                }
            }
        }

        // Display search results
        if (output.isEmpty()) {
            // Print message if no doctors found
            System.out.println("No doctors found.");
        } else {
            // Print header for results
            System.out.println("\nFound doctors:");
            // Iterate through matching doctors and print their details
            for (Doctor dr : output) {
                System.out.printf("Code: %s, Name: %s, Specialization: %s, Availability: %d\n",
                        dr.getCode(), dr.getName(), dr.getSpecialization(), dr.getAvailability());
            }
        }

        // Return the list of matching doctors
        return output;
    }

    // Helper method to check if an attribute matches the search input
    public boolean matchesInput(String attr, String input) {
        // Convert attribute to lowercase and check if it contains the input
        return attr.toUpperCase().contains(input);
    }

    // Helper method to check if availability matches the search input
    public boolean matchesAvailability(int availability, String input) {
        try {
            // Try to convert input to integer and compare with availability
            return availability == Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Return false if input is not a valid number
            return false;
        }
    }

    // Method to check if a doctor code exists in the database
    public boolean chkDrCodeExist(String input) {
        // Return true if code exists in HashMap, false otherwise
        return drList.containsKey(input);
    }

    // Method to check if the doctor database is empty
    public boolean drListIsEmpty() {
        // Return true if HashMap is empty, false otherwise
        return drList.isEmpty();
    }
    
    public Doctor getDr(String code){
        return drList.get(code);
    }
}
