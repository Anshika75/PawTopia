package com.virtualpet.pet;

import java.util.Scanner;

public class PetName {
    public static String namePet() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWhat would you like to name your pet?");
        System.out.print("Enter a name: ");
        String petName = scanner.nextLine().trim();

        // Validate input: Ensure it's not empty, doesnt start with a number, doesnt end with a special character, doesnt start with a special character or doesnt have just one character
        while (petName.isEmpty() || petName.matches("^[0-9].*") || petName.matches("^[^a-zA-Z0-9].*") || petName.matches(".*[^a-zA-Z0-9]$") || (petName.length() == 1)) {
            if (petName.isEmpty()){
                System.out.println("\nName cannot be empty. Please enter a valid name:");
            } else if (petName.matches("^[0-9].*")) {
                System.out.println("\nName cannot start with a number. Please enter a valid name:");
            } else if (petName.matches("^[^a-zA-Z0-9].*")) {
                System.out.println("\nName must start with a letter or number. Please enter a valid name:");
            } else if (petName.matches(".*[^a-zA-Z0-9]$")) {
                System.out.println("\nName must end with a letter or number. Please enter a valid name:");
            } else if (petName.length() == 1) {
                System.out.println("\nName must be longer than one character. Please enter a valid name:");
            }

            System.out.print("Enter a name: ");
            petName = scanner.nextLine().trim();
        }

        System.out.println("\nGreat! Your pet's name is " + petName + ".");
        return petName;
    }
}
