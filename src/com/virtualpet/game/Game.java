package com.virtualpet.game;

import java.util.Scanner;
import com.virtualpet.activity.Activity;
import com.virtualpet.asciiart.PetArt;
import com.virtualpet.pet.PetType;
import com.virtualpet.pet.PetBreed;
import com.virtualpet.pet.PetColor;
import com.virtualpet.pet.PetName;
import com.virtualpet.pet.Pet;
import com.virtualpet.shop.Shop;

public class Game {
    public static void main(String[] args) {
        System.out.println("Welcome to the Virtual Pet Game!");

        // User selects pet type, breed, color, and name
        PetType selectedPet = PetType.choosePet();
        String selectedBreed = PetBreed.chooseBreed(selectedPet);
        String selectedColor = PetColor.chooseColor(selectedPet, selectedBreed);
        String petName = PetName.namePet();

        // Create Pet object with name from PetName class and initial bank balance
        Pet pet = new Pet(selectedPet, petName, 1000); // Initialize Pet with name, type, and bank balance

        // Display pet details
        System.out.println("\nYour pet details:");
        System.out.println("Type: " + selectedPet.name());
        System.out.println("Breed: " + selectedBreed);
        System.out.println("Color: " + selectedColor);
        System.out.println("Name: " + pet.getName()); // Use the getName() method to display the pet's name

        // Allow initial item purchases
        Shop.purchaseItems(pet);

        // Display ASCII art and game start countdown
        PetArt.printPetArt(selectedPet, pet.getName()); // Use pet's name
        System.out.println("\nGame starting...");
        countdown(5);

        pet.displayMetrics();

        // Main game loop
        Scanner scanner = new Scanner(System.in);
        boolean gameActive = true;

        while (gameActive) {
            System.out.println("\nWhat would you like to do next?");
            System.out.println("1. Perform an activity\n2. Visit the shop\n3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1 -> {
                    System.out.println("\nChoose an activity for your pet:");
                    System.out.println("1. Sleep\n2. Eating\n3. Bathing\n4. Playing");
                    System.out.print("Enter your choice: ");
                    int activityChoice = scanner.nextInt();

                    Activity selectedActivity = switch (activityChoice) {
                        case 1 -> Activity.SLEEP;
                        case 2 -> Activity.EATING;
                        case 3 -> Activity.BATHING;
                        case 4 -> Activity.PLAYING;
                        default -> null;
                    };

                    if (selectedActivity != null) {
                        pet.performActivity(selectedActivity);
                        PetArt.printPetArt(selectedPet, petName, selectedActivity.name());
                        countdown(5);
                    } else {
                        System.out.println("Invalid activity choice. Please try again.");
                    }
                }
                case 2 -> Shop.purchaseItems(pet); // Allow item purchase
                case 3 -> {
                    System.out.println("\nExiting the game. See you next time!");
                    gameActive = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            // Display updated metrics
            if (gameActive) {
                pet.displayMetrics();
            }
        }

        scanner.close();
    }

    private static void countdown(int seconds) {
        try {
            for (int i = seconds; i > 0; i--) {
                System.out.println("... " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Countdown interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
