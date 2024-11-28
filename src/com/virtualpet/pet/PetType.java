package com.virtualpet.pet;

import java.util.Scanner;

public enum PetType {
    DOG,
    CAT,
    BIRD;

    // Method to prompt user to select a pet
    public static PetType choosePet() {
        Scanner scanner = new Scanner(System.in);
        PetType selectedPet = null;

        String PetTypeTxt = """
                Choose a pet to start with:
                1. Dog
                2. Cat
                3. Bird
                Enter your choice (1/2/3):
                """;    

        // Display pet options
        System.out.println(PetTypeTxt);
        
        // Validate user input
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> selectedPet = PetType.DOG;
                case 2 -> selectedPet = PetType.CAT;
                case 3 -> selectedPet = PetType.BIRD;
                default -> {
                    System.out.println("Invalid choice, defaulting to Dog.");
                    selectedPet = PetType.DOG;
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input! Defaulting to Dog.");
            selectedPet = PetType.DOG;
        }

        return selectedPet;
    }
}
