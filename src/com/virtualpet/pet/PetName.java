package com.virtualpet.pet;

import java.util.Scanner;

public class PetName {
    public static String namePet() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWhat would you like to name your pet?");
        System.out.print("Enter a name: ");
        String petName = scanner.nextLine().trim();

        // Validate input: Ensure it's not empty
        while (petName.isEmpty()) {
            System.out.println("\nName cannot be empty. Please enter a valid name:");
            System.out.print("Enter a name: ");
            petName = scanner.nextLine().trim();
        }

        System.out.println("\nGreat! Your pet's name is " + petName + ".");
        return petName;
    }
}
