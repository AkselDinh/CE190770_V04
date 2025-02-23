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

    /**
     * Adds a new doctor to the database
     *
     * @param code the doctor's code
     * @param name the doctor's name
     * @param spec the doctor's specialization
     * @param avail the doctor's availability
     */
    public void addDr(String code, String name, String spec, int avail) {
        // Creates a new Doctor object with the provided parameters
        Doctor doctor = new Doctor(code, name, spec, avail);
        // Adds the doctor to the HashMap with code as key
        drList.put(code, doctor);
    }

    /**
     * Updates an existing doctor's information
     *
     * @param code the doctor's code
     * @param name the new name
     * @param spec the new specialization
     * @param avail the new availability
     */
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

    /**
     * Deletes a doctor from the database
     *
     * @param code the code of the doctor to delete
     */
    public void deleteDr(String code) {
        // Removes the doctor from HashMap using their code
        drList.remove(code);
    }

    /**
     * Method to search for doctors based on input criteria
     *
     * @param input String of Doctor's info to be searched
     * @return List of matching search result
     */
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

        // Return the list of matching doctors
        return output;
    }

    /**
     * Helper method to check if an attribute matches the search input
     *
     * @param attr attribute of Doctor to be compared with input
     * @param input desired search String
     * @return true if attribute contains input, false otherwise
     */
    public boolean matchesInput(String attr, String input) {
        // Convert attribute to lowercase and check if it contains the input
        return attr.toUpperCase().contains(input);
    }

    /**
     * Helper method to check if availability matches the search input
     *
     * @param availability of Doctor to be compared with input
     * @param input desired search int
     * @return true if availability contains input, false otherwise or by
     * exception
     */
    public boolean matchesAvailability(int availability, String input) {
        try {
            // Try to convert input to integer and compare with availability
            return availability == Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Return false if input is not a valid number
            return false;
        }
    }

    /**
     * Method to check if a doctor code exists in the database
     *
     * @param input String to be searched in database
     * @return true if found, false otherwise
     */
    public boolean chkDrCodeExist(String input) {
        // Return true if code exists in HashMap, false otherwise
        return drList.containsKey(input);
    }

    /**
     * Method to check if the doctor database is empty
     *
     * @return true if database is empty, false otherwise
     */
    public boolean drListIsEmpty() {
        // Return true if HashMap is empty, false otherwise
        return drList.isEmpty();
    }

    /**
     * Gets a doctor from the database by code
     *
     * @param code the doctor's code
     * @return the Doctor object, or null if not found
     */
    public Doctor getDr(String code) {
        return drList.get(code);
    }
}
