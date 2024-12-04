package com.virtualpet.pet;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public enum PetColor {
    // Enum entries for each pet type with specific color options
    DOG(Arrays.asList("Black", "White", "Brown", "Golden", "Gray", "Spotted")),
    CAT(Arrays.asList("Black", "White", "Orange", "Gray", "Spotted")),
    BIRD(Arrays.asList("Green (Parrot)", "Yellow (Canary)", "White & Gray (Cockatiel)"));

    private final List<String> availableColors;

    // Constructor for assigning available colors
    PetColor(List<String> availableColors) {
        this.availableColors = availableColors;
    }

    // Getter for available colors
    public List<String> getAvailableColors() {
        return availableColors;
    }

    // Static method to prompt user to choose a color based on pet type
    public static String chooseColor(PetType petType, String breed) {
        Scanner scanner = new Scanner(System.in);
        PetColor petColor = switch (petType) {
            case DOG -> DOG;
            case CAT -> CAT;
            case BIRD -> BIRD;
        };

        // Display available colors
        System.out.println("\nChoose a color for your " + petType.name() + ":\n");
        List<String> availableColors = petColor.getAvailableColors();

        // Adjust bird color options based on the breed
        if (petType == PetType.BIRD) {
            if (breed.equalsIgnoreCase("Parrot")) {
                availableColors = Arrays.asList("Green");
            } else if (breed.equalsIgnoreCase("Canary")) {
                availableColors = Arrays.asList("Yellow");
            } else if (breed.equalsIgnoreCase("Cockatiel")) {
                availableColors = Arrays.asList("White", "Gray");
            }
        }

        for (int i = 0; i < availableColors.size(); i++) {
            System.out.println((i + 1) + ". " + availableColors.get(i));
        }

        System.out.print("\nEnter your choice: \n\n");
        String selectedColor = availableColors.get(0); // Default to the first color
        try {
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= availableColors.size()) {
                selectedColor = availableColors.get(choice - 1);
            } else {
                System.out.println("\nInvalid choice, defaulting to " + selectedColor + ".");
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input! Defaulting to " + selectedColor + ".");
        }

         ;
        return selectedColor;
    }
}
