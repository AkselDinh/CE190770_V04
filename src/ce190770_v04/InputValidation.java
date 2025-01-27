/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce190770_v04;

import java.util.Scanner;

/**
 * V04 - Doctor Management Program This class validate input
 *
 * @author Dinh Cong Phuc - CE190770 - 27/Jan/2025
 */
public class InputValidation {

    // Scanner object for reading user input
    static Scanner sc = new Scanner(System.in);

    /**
     * Gets and validates integer input from user Continues to prompt until
     * valid integer is entered
     *
     * @return valid integer input from user
     */
    public static int getIntInput() {
        // Loop until valid integer is entered
        while (true) {
            try {
                // Read and parse user input, removing whitespace
                int in = Integer.parseInt(sc.nextLine().trim());
                if (in >= 0) {
                    return in;
                } else {
                    System.out.print("Input must be larger or equals 0. Please try again: ");
                }
                // If exception occur, handles in catch block.
            } catch (Exception e) {
                // Prompt user to retry on invalid input
                System.out.print("Input is not a valid integer. Please try again: ");
            }
        }
    }
    
    public static Integer getIntInputOptional(){
        // Loop until valid integer is entered
        while(true){
            // Read and parse user input, removing whitespace
            String input = sc.nextLine().trim().replaceAll(" +", "");
            // Check for empty input
            if(input.isEmpty()){
                return null;
            }
            try {
                int in = Integer.parseInt(input);
                if(in >= 0){
                return in;
                } else {
                    System.out.print("Input must be larger or equals 0. Please try again: ");
                }
                // If exception occur, handles in catch block.
            } catch (Exception e){
                // Prompt user to retry on invalid input
                System.out.print("Input is not a valid integer. Please try again: ");
            }
        }
    }

    // Method to validate and get doctor code input
    public static String getDocStringInput() {
        // Continuous loop until valid input is received
        while (true) {
            // Gets non-empty string input
            String in = getStringNonEmpty().toUpperCase();
            // Checks if input starts with "DOC"
            if (in.startsWith("DOC")) {
                // Returns valid doctor code
                return in;
            } else {
                // Prompts user to enter correct format
                System.out.print("Doctor code must start with 'DOC'. Please try again: ");
            }
        }
    }

    // Method to get and validate non-empty string input
    public static String getStringNonEmpty() {
        // Continuous loop until valid input is received
        while (true) {
            // Reads input, trims whitespace, and normalizes spaces
            String in = sc.nextLine().trim().replaceAll(" +", " ");
            // Checks if input is not empty
            if (!in.isEmpty()) {
                // Returns valid input
                return in;
            } else {
                // Prompts user to enter non-empty input
                System.out.print("Input should not be empty. Please try again: ");
            }
        }
    }
}
