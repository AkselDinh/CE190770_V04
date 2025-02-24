/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce190770_v04;

import java.util.List;
import java.util.Scanner;

/**
 * V04 - Doctor Management Program This is the class contains program loop and
 * displaying UI
 *
 * @author Dinh Cong Phuc - CE190770 - 27/Jan/2025
 */
// Main class declaration for menu interface
public class MenuUI {

    // Creates Scanner object for reading user input
    Scanner sc = new Scanner(System.in);
    // Declares DoctorDTB object to manage doctor database
    DoctorDTB drDTB;
    // Stores the user's menu selection
    private int selection;

    /**
     * Main program loop method. Continues until user selects exit option
     */
    public void loop() {
        // Initializes the doctor database
        drDTB = new DoctorDTB();
        // Continues loop until user selects exit (5)
        while (selection != 5) {
            // Displays the menu
            printMenu();
            // Handles user selection
            selection();
        }
    }

    /**
     * Handles user menu selection and executes corresponding actions
     */
    public void selection() {
        // Prompts user for input
        System.out.println("Please enter your selection: ");
        // Gets and validates user input
        selection = InputValidation.getIntInput();

        // Processes user selection
        switch (selection) {
            case 1:
                // Calls method to add new doctor
                addDoctor();
                break;
            case 2:
                // Calls method to update existing doctor
                updateDoctor();
                break;
            case 3:
                // Calls method to delete doctor
                deleteDoctor();
                break;
            case 4:
                // Calls method to search for doctors
                searchDoctor();
                break;
            case 5:
                // Exit program
                break;
            default:
                // Displays error for invalid selection
                System.out.println("Selection not exist, please choose between 1~5.");
        }
    }

    /**
     * Displays the main menu options to the user
     */
    public void printMenu() {
        System.out.println("======= Doctor Management =======");
        System.out.println("\t1.Add Doctor");
        System.out.println("\t2.Update Doctor");
        System.out.println("\t3.Delete Doctor");
        System.out.println("\t4.Search Doctor");
        System.out.println("\t5.Exit");
    }

    /**
     * Handles the process of adding a new doctor to the database
     */
    public void addDoctor() {
        // Displays add doctor header
        System.out.println("------- Add Doctor -------");
        // Prompts for and reads doctor code
        System.out.print("Enter Code: ");
        String code = readCode();

        // Prompts for and reads doctor name
        System.out.print("Enter Name: ");
        String name = InputValidation.getStringNonEmpty();

        // Prompts for and reads specialization
        System.out.print("Enter Specialization: ");
        String spec = InputValidation.getStringNonEmpty();

        // Prompts for and reads availability
        System.out.print("Enter Availability: ");
        int avail = InputValidation.getIntInput();

        // Adds new doctor to database
        drDTB.addDr(code, name, spec, avail);
    }

    /**
     * Handles the process of updating an existing doctor's information
     */
    public void updateDoctor() {
        if (!drDTB.drListIsEmpty()) {
            // Continues until valid update is completed
            while (true) {
                // Prompts for doctor code
                System.out.print("Enter Doctor code to update info: ");
                String code = InputValidation.getDocStringInput();
                // Checks if doctor exists
                if (drDTB.chkDrCodeExist(code)) {
                    Doctor doc = drDTB.getDr(code);
                    // Prompts for and reads new name
                    System.out.print("Enter New Name: ");
                    // Take input from console and validate
                    String input = sc.nextLine().trim().replaceAll(" +", " ");
                    // Take validated input and adapt to doctor name
                    String name = input.isEmpty() ? doc.getName() : input;

                    // Prompts for and reads new specialization
                    System.out.print("Enter New Specialization: ");
                    // Take input from console and validate
                    input = sc.nextLine().trim().replaceAll(" +", " ");
                    // Take validated input and adapt to doctor specialization
                    String spec = input.isEmpty() ? doc.getSpecialization() : input;

                    // Prompts for and reads new availability
                    System.out.print("Enter New Availability: ");
                    // Take input from console and validate
                    Integer availIn = InputValidation.getIntInputOptional();
                    // Take validated input and adapt to doctor availability
                    int avail = (availIn == null) ? doc.getAvailability() : availIn;

                    // Updates doctor information
                    drDTB.updateDr(code, name, spec, avail);
                    // Notify about changes
                    System.out.println("Doctor info updated.");
                    // Break out of loops
                    break;
                } else {
                    // Displays error for non-existent doctor
                    System.out.println("Doctor code does not exist, please try again.");
                }
            }
        } else {
            // If user never add a doctor, notify about empty database
            System.out.println("Doctor Database is empty, please add Doctor first");
        }
    }

    /**
     * Handles the process of deleting a doctor from the database
     */
    public void deleteDoctor() {
        // Checks if database is not empty
        if (!drDTB.drListIsEmpty()) {
            // Continues until valid deletion is completed
            while (true) {
                // Prompts for doctor code
                System.out.print("Enter Doctor code to delete: ");
                // Take input from console and validate
                String code = InputValidation.getDocStringInput();
                // Checks if doctor exists
                if (drDTB.chkDrCodeExist(code)) {
                    // Deletes doctor and confirms
                    drDTB.deleteDr(code);
                    // Notify about changes and break out of loops
                    System.out.println("Doctor has been deleted from database.");
                    break;
                } else {
                    // Displays error for non-existent doctor
                    System.out.println("Doctor code does not exist, please try again.");
                }
            }
        } else {
            // Displays error for empty database
            System.out.println("Doctor Database is empty, please add Doctor first");
        }
    }

    /**
     * Handles the process of searching for doctors in the database
     */
    public void searchDoctor() {
        // Checks if database is not empty
        if (!drDTB.drListIsEmpty()) {
            // Prompts for search term
            System.out.print("Please enter words to be searched: ");
            String searchTerm = sc.nextLine();
            // Displays search results header
            System.out.println("\n------------------------ Search Results ------------------------\n");
            // Performs search and print out table
            printTable(drDTB.searchDr(searchTerm));
            // Displays search results footer
            System.out.println("\n----------------------------------------------------------------\n");
        } else {
            // Displays error for empty database
            System.out.println("Doctor Database is empty, please add Doctor first");
        }
    }
    
    /**
     * Take Doctor List and print out formatted table
     * 
     * @param lst to read data from
     */
    public void printTable(List<Doctor> lst){
        // Display search results
        if (lst.isEmpty()) {
            // Print message if no doctors found
            System.out.println("No doctors found.");
        } else {
            // Print header for results
            System.out.printf("+ %-6s + %-18s + %-15s + %-12s +\n", "Code", "Name", "Specialization", "Availability");
            // Iterate through matching doctors and print their details
            for (Doctor dr : lst) {
                // Formatted input for table
                System.out.printf("| %-6s | %-18s | %-15s | %-12s |\n",
                        dr.getCode(), dr.getName(), dr.getSpecialization(), dr.getAvailability());
            }
        }
    }

    /**
     * Reads and validates a unique doctor code
     *
     * @return valid and unique doctor code string
     */
    public String readCode() {
        // Continues until valid code is entered
        while (true) {
            // Gets doctor code input
            String in = InputValidation.getDocStringInput();
            // Checks if code already exists
            if (drDTB.chkDrCodeExist(in)) {
                // Prompts for new code if duplicate
                System.out.printf("Doctor Code '%s' already exist in database, please enter again: ", in);
            } else {
                // Returns valid code
                return in;
            }
        }
    }
}
