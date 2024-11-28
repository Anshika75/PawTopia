package com.virtualpet.pet;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetBreed {
    // Static method to get breeds based on PetType
    public static List<String> getBreeds(PetType petType) {
        return switch (petType) {
            case DOG -> Arrays.asList("Golden Retriever", "German Shepherd", "Bulldog");
            case CAT -> Arrays.asList("Persian", "Siamese", "Maine Coon");
            case BIRD -> Arrays.asList("Parrot", "Canary", "Cockatiel");
        };
    }

    // Static method to choose a breed
    public static String chooseBreed(PetType petType) {
        Scanner scanner = new Scanner(System.in);
        List<String> availableBreeds = getBreeds(petType);

        System.out.println("Choose a breed for your " + petType.name() + ":");
        for (int i = 0; i < availableBreeds.size(); i++) {
            System.out.println((i + 1) + ". " + availableBreeds.get(i));
        }
        System.out.print("Enter your choice: ");

        String selectedBreed = availableBreeds.get(0); // Default to the first breed
        try {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= availableBreeds.size()) {
                selectedBreed = availableBreeds.get(choice - 1);
            } else {
                System.out.println("Invalid choice, defaulting to " + selectedBreed + ".");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Defaulting to " + selectedBreed + ".");
        }

        return selectedBreed;
    }
}
