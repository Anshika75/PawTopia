package com.virtualpet.config;

import java.util.*;

import com.virtualpet.game.Game;
import com.virtualpet.pet.Pet;
import com.virtualpet.pet.PetType;

public class VaccinationManager {

    private static final Map<PetType, Map<Double, String>> VACCINATION_SCHEDULE = Map.of(
    PetType.DOG, Map.ofEntries(
        Map.entry(0.6, "DHPP"),
        Map.entry(1.0, "Rabies"),
        Map.entry(2.0, "Bordetella"),
        Map.entry(3.0, "Leptospirosis"),
        Map.entry(4.0, "Canine Influenza (H3N2/H3N8)"),
        Map.entry(5.0, "Lyme"),
        Map.entry(6.0, "DHPP - 2"),
        Map.entry(7.0, "Rabies - 2"),
        Map.entry(8.0, "Bordetella - 2"),
        Map.entry(9.0, "Leptospirosis"),
        Map.entry(10.0, "Lyme"),
        Map.entry(11.0, "Canine Influenza (H3N2/H3N8) - 2"),
        Map.entry(12.0, "Antibody")
    ),
    PetType.CAT, Map.ofEntries(
        Map.entry(0.6, "FVRCP"),
        Map.entry(1.0, "Rabies"),
        Map.entry(2.0, "FeLV"),
        Map.entry(3.0, "Leptospirosis"),
        Map.entry(4.0, "Chlamydia"),
        Map.entry(5.0, "Lyme"),
        Map.entry(6.0, "FVRCP - 2"),
        Map.entry(7.0, "Rabies - 2"),
        Map.entry(8.0, "FeLV - 2"),
        Map.entry(9.0, "Leptospirosis"),
        Map.entry(10.0, "Lyme"),
        Map.entry(11.0, "Chlamydia - 2"),
        Map.entry(12.0, "Antibody")
    ),
    PetType.BIRD, Map.ofEntries(
        Map.entry(0.6, "Polyomavirus"),
        Map.entry(1.0, "Avian Influenza"),
        Map.entry(2.0, "PBFD"),
        Map.entry(3.0, "Chlamydophila"),
        Map.entry(4.0, "Newcastle Disease"),
        Map.entry(5.0, "Energy Booster"),
        Map.entry(6.0, "Polyomavirus - 2"),
        Map.entry(7.0, "Avian Influenza - 2")
    )
);

    // Check the vaccination based on the pet's type and age
    public static void checkVaccination(Pet pet) {
        double petAge = pet.getAge();
        PetType petType = pet.getPetType();

        Map<Double, String> schedule = VACCINATION_SCHEDULE.get(petType);

        if (schedule != null && schedule.containsKey(petAge)) {
            String vaccine = schedule.get(petAge);
            triggerVaccination(pet, vaccine, getVaccineCost(vaccine));
        }
    }

    // Print the vaccination schedule for a pet type
    public static void printVaccinationSchedule(Pet pet) {
        PetType petType = pet.getPetType();

        System.out.println("\n=== Vaccination Schedule for " + petType + " ===");
        Map<Double, String> schedule = VACCINATION_SCHEDULE.get(petType);

        if (schedule != null) {
            schedule.forEach((age, vaccine) -> 
                System.out.println(age + " years: " + vaccine + " Vaccination - $" + getVaccineCost(vaccine)));
        } else {
            System.out.println("No vaccination schedule available for this pet type.");
        }
    }

    // Get vaccine cost based on the vaccine name
    private static int getVaccineCost(String vaccine) {
        return switch (vaccine) {
            case "DHPP", "FVRCP", "Polyomavirus" -> 100;
            case "Rabies", "Avian Influenza", "Lyme" -> 150;
            case "Bordetella", "FeLV", "PBFD" -> 200;
            case "Leptospirosis", "Chlamydia", "Newcastle Disease" -> 250;
            case "Canine Influenza (H3N2/H3N8)", "Energy Booster" -> 200;
            case "Antibody" -> 300;
            case "DHPP - 2", "FVRCP - 2", "Polyomavirus - 2" -> 200;
            case "Rabies - 2", "Avian Influenza - 2" -> 350;
            default -> 100; // Default cost if vaccine name is unknown
        };
    }

    // Trigger vaccination logic
    private static void triggerVaccination(Pet pet, String vaccineName, int vaccineCost) {
        System.out.println(pet.getName() + " needs the " + vaccineName + "vaccination. Cost: $" + vaccineCost);
        System.out.println("Would you like to vaccinate " + pet.getName() + "? (1 = Yes, 2 = No)");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            if (pet.getBankBalance() >= vaccineCost) {
                pet.setBankBalance(pet.getBankBalance() - vaccineCost);
                System.out.println(
                        "Vaccination successful! " + pet.getName() + " is now vaccinated with " + vaccineName + ".");
            } else {
                System.out.println("You forgot to keep check on balance and were busy in leisure...");
                System.out.println("Game Over! You don't have enough balance for vaccination.");
                endGame();
            }
        } else {
            System.out.println("You chose not to vaccinate " + pet.getName() + ".");
            System.out.println(pet.getName() + " is sick and needs a veternarian. It will cost you $750");
            System.out.println("Would you like to take " + pet.getName() + " to the veternarian? (1 = Yes, 2 = No)");
            int choice2 = scanner.nextInt();
            if (choice2 == 1) {
                if (pet.getBankBalance() >= 750) {
                    pet.setBankBalance(pet.getBankBalance() - 750);
                    System.out.println(
                            "You took " + pet.getName() + " to the veternarian. " + pet.getName() + " is now healthy.");
                } else {
                    System.out.println("You forgot to keep check on balance and were busy in leisure...");
                    System.out.println("Game Over! You don't have enough balance for vaccination.");
                    // End the game with an option to restart or quit
                    endGame();
                }
            } else {
                System.out.println("You chose not to take " + pet.getName() + " to the veternarian.");
                System.out.println("Game Over! Your pet is sick and needs a veternarian.");
                // End the game with an option to restart or quit
                endGame();
            }
        }
    }

    // End game method, ask if player wants to restart or quit
    private static void endGame() {
        System.out.println("Would you like to restart or quit? (1 = Restart, 2 = Quit)");
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
