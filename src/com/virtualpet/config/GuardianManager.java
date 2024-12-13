package com.virtualpet.config;

import java.util.Map;
import java.util.Scanner;

import com.virtualpet.game.Game;
import com.virtualpet.pet.Pet;
import com.virtualpet.pet.PetType;

public class GuardianManager {

    private static final Map<PetType, Double> AGE_THRESHOLD = Map.of(
        PetType.DOG, 12.4,
        PetType.CAT, 12.2,
        PetType.BIRD, 7.6
    );

    // Check if the pet has reached the age limit and should ascend as a guardian
    public static void checkGuardianStatus(Pet pet) {
        double petAge = pet.getAge();
        PetType petType = pet.getPetType();

        Double threshold = AGE_THRESHOLD.get(petType);

        if (threshold != null && petAge >= threshold) {
            triggerGuardianAscension(pet);
        }
    }

    // Trigger the guardian ascension logic
    private static void triggerGuardianAscension(Pet pet) {
        pet.setGuardian(true);
        System.out.println("\n" + pet.getName() + " has reached the age limit and is now ascending as a guardian.");
        System.out.println("Congratulations! You won as you were able to take care of your pet till the very end.");
        endGame();
    }

    // End game method, ask if player wants to restart or quit
    private static void endGame() {
        System.out.println("\n Would you like to restart or quit? (1 = Restart, 2 = Quit)");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Restarting the game...");
            Game.gameStart();
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
        }
    }
}