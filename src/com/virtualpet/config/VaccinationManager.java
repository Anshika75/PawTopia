
package com.virtualpet.config;

import java.util.Scanner;

import com.virtualpet.game.Game;
import com.virtualpet.pet.Pet;
import com.virtualpet.pet.PetType;

public class VaccinationManager {

    // Check the vaccination based on the pet's type and age
    public static void checkVaccination(Pet pet) {
        double petAge = pet.getAge();
        PetType petType = pet.getPetType();

        switch (petType) {
            case DOG:
                checkDogVaccination(pet, petAge);
                break;
            case CAT:
                checkCatVaccination(pet, petAge);
                break;
            case BIRD:
                checkBirdVaccination(pet, petAge);
                break;
            default:
                System.out.println("Unknown pet type. Cannot check vaccinations.");
        }
    }

    // Handle Dog Vaccinations
    private static void checkDogVaccination(Pet pet, double petAge) {
        if (petAge >= 0.6 && petAge < 1.0) {
            triggerVaccination(pet, "DHPP", 200);
        } else if (petAge >= 1.0 && petAge < 2.0) {
            triggerVaccination(pet, "Rabies", 150);
        } else if (petAge >= 2.0 && petAge < 3.0) {
            triggerVaccination(pet, "Bordetella", 100);
        } else if (petAge >= 3.0 && petAge < 4.0) {
            triggerVaccination(pet, "Leptospirosis", 250);
        } else if (petAge >= 4.0 && petAge < 5.0) {
            triggerVaccination(pet, "Canine Influenza (H3N2/H3N8)", 200);
        } else if (petAge >= 5.0 && petAge < 6.0) {
            triggerVaccination(pet, "Lyme", 150);
        } else if (petAge >= 6.0 && petAge < 7.0) {
            triggerVaccination(pet, "DHPP - 2", 200);
        } else if (petAge >= 7.0 && petAge < 8.0) {
            triggerVaccination(pet, "Rabies - 2", 350);
        } else if (petAge >= 8.0 && petAge < 9.0) {
            triggerVaccination(pet, "Bordetella - 2", 200);
        } else if (petAge >= 9.0 && petAge < 10.0) {
            triggerVaccination(pet, "Leptospirosis", 150);
        } else if (petAge >= 10.0 && petAge < 11.0) {
            triggerVaccination(pet, "Lyme", 250);
        } else if (petAge >= 11.0 && petAge < 12.0) {
            triggerVaccination(pet, "Canine Influenza (H3N2/H3N8) - 2", 100);
        } else if (petAge >= 12.0) {
            triggerVaccination(pet, "Antibody", 300);
        }
    }

    // Handle Cat Vaccinations
    private static void checkCatVaccination(Pet pet, double petAge) {
        if (petAge >= 0.6 && petAge < 1.0) {
            triggerVaccination(pet, "FVRCP", 100);
        } else if (petAge >= 1.0 && petAge < 2.0) {
            triggerVaccination(pet, "Rabies", 150);
        } else if (petAge >= 2.0 && petAge < 3.0) {
            triggerVaccination(pet, "FeLV", 100);
        } else if (petAge >= 3.0 && petAge < 4.0) {
            triggerVaccination(pet, "Leptospirosis", 250);
        } else if (petAge >= 4.0 && petAge < 5.0) {
            triggerVaccination(pet, "Chlamydia", 200);
        } else if (petAge >= 5.0 && petAge < 6.0) {
            triggerVaccination(pet, "Lyme", 150);
        } else if (petAge >= 6.0 && petAge < 7.0) {
            triggerVaccination(pet, "FVRCP - 2", 200);
        } else if (petAge >= 7.0 && petAge < 8.0) {
            triggerVaccination(pet, "Rabies - 2", 350);
        } else if (petAge >= 8.0 && petAge < 9.0) {
            triggerVaccination(pet, "FeLV - 2", 200);
        } else if (petAge >= 9.0 && petAge < 10.0) {
            triggerVaccination(pet, "Leptospirosis", 150);
        } else if (petAge >= 10.0 && petAge < 11.0) {
            triggerVaccination(pet, "Lyme", 250);
        } else if (petAge >= 11.0 && petAge < 12.0) {
            triggerVaccination(pet, "Chlamydia - 2", 100);
        } else if (petAge >= 12.0) {
            triggerVaccination(pet, "Antibody", 300);
        }
    }

    // Handle Bird Vaccinations
    private static void checkBirdVaccination(Pet pet, double petAge) {
        if (petAge >= 0.6 && petAge < 1.0) {
            triggerVaccination(pet, "Polyomavirus", 100);
        } else if (petAge >= 1.0 && petAge < 2.0) {
            triggerVaccination(pet, "Avian Influenza", 150);
        } else if (petAge >= 2.0 && petAge < 3.0) {
            triggerVaccination(pet, "PBFD", 100);
        } else if (petAge >= 3.0 && petAge < 4.0) {
            triggerVaccination(pet, "Chlamydophila", 250);
        } else if (petAge >= 4.0 && petAge < 5.0) {
            triggerVaccination(pet, "Newcastle Disease", 200);
        } else if (petAge >= 5.0 && petAge < 6.0) {
            triggerVaccination(pet, "Energy Booster", 150);
        } else if (petAge >= 6.0 && petAge < 7.0) {
            triggerVaccination(pet, "Polyomavirus - 2", 200);
        } else if (petAge >= 7.0 && petAge < 8.0) {
            triggerVaccination(pet, "Avian Influenza - 2", 350);
        }
    }

    // Trigger vaccination check
    private static void triggerVaccination(Pet pet, String vaccineName, int vaccineCost) {
        // Check if the pet has already been vaccinated (for simplicity, we assume each
        // pet gets vaccinated once per age range)
        // If not, ask the player to vaccinate the pet

        if (pet.getBankBalance() >= vaccineCost) {
            System.out.println(pet.getName() + " needs the " + vaccineName + ". Cost: $" + vaccineCost);
            pet.setBankBalance(pet.getBankBalance() - vaccineCost); // Deduct the cost from the bank balance
            System.out.println(
                    "Vaccination successful! " + pet.getName() + " is now vaccinated with " + vaccineName + ".");
        } else {
            System.out.println("You forgot to keep check on balance and were busy in leisure...");
            System.out.println("Game Over! You don't have enough balance for vaccination.");
            // End the game with an option to restart or quit
            endGame();
        }
    }

    // End game method, ask if player wants to restart or quit
    private static void endGame() {
        System.out.println("Would you like to restart or quit? (1 = Restart, 2 = Quit)");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 1) {
            // Restart game logic (you can reset the pet stats here)
            System.out.println("Restarting the game...");
            Game.gameStart();
        } else {
            System.out.println("Exiting the game. Goodbye!");
            System.exit(0);
        }
    }
}