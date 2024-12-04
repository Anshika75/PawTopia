package com.virtualpet.shop;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class PointsShop {

    static class Boost {
        String name;
        int cost; // Cost in bank balance
        String description;
        Runnable applyBoost;

        Boost(String name, int cost, String description, Runnable applyBoost) {
            this.name = name;
            this.cost = cost;
            this.description = description;
            this.applyBoost = applyBoost;
        }

        @Override
        public String toString() {
            return name + " - Cost: $" + cost + " | Description: " + description;
        }
    }

    private static Boost[] boosts;

    static {
        boosts = new Boost[] {
            new Boost("Sleep Boost", 150, "Increases Sleep Points by 50.", () -> {}),
            new Boost("Play Boost", 150, "Increases Play Points by 50.", () -> {}),
            new Boost("Eating Boost", 150, "Increases Eating Points by 50.", () -> {}),
            new Boost("Energy Boost", 250, "Increases Energy Points by 50.", () -> {}),
            new Boost("Happiness Boost", 200, "Increases Happiness Points by 50.", () -> {}),
            new Boost("Bathing Boost", 150, "Increases Bathing Points by 50.", () -> {})
        };
    }

    public static void displayPointsShop(Pet pet) {
        System.out.println("\n=== Points Shop ===");
        System.out.println("Your Current Bank Balance: $" + pet.getBankBalance());
        System.out.println("\nAvailable Boosts:");
        for (int i = 0; i < boosts.length; i++) {
            System.out.println((i + 1) + ". " + boosts[i]);
        }
    }

    public static void purchaseBoost(Pet pet) {
        Scanner scanner = new Scanner(System.in);

        displayPointsShop(pet);

        System.out.println("\nEnter the number of the boost to purchase, or press Enter to skip:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("You chose not to purchase any boosts.");
            return;
        }

        try {
            int boostNumber = Integer.parseInt(input) - 1;

            if (boostNumber < 0 || boostNumber >= boosts.length) {
                System.out.println("Invalid boost number.");
                return;
            }

            Boost selectedBoost = boosts[boostNumber];

            if (pet.getBankBalance() >= selectedBoost.cost) {
                pet.setBankBalance(pet.getBankBalance() - selectedBoost.cost);
                System.out.println("Purchased: " + selectedBoost.name + " for $" + selectedBoost.cost + ".\nBoosting " + pet.getName());

                // Apply the boost effect
                switch (boostNumber) {
                    case 0 -> pet.setSleepPoints(pet.getSleepPoints() + 50);
                    case 1 -> pet.setPlayPoints(pet.getPlayPoints() + 50);
                    case 2 -> pet.setEatingPoints(pet.getEatingPoints() + 50);
                    case 3 -> pet.setEnergyPoints(pet.getEnergyPoints() + 50);
                    case 4 -> pet.setHappinessPoints(pet.getHappinessPoints() + 50);
                    case 5 -> pet.setBathingPoints(pet.getBathingPoints() + 50);
                }
            } else {
                System.out.println("Not enough bank balance to purchase: " + selectedBoost.name);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid boost number.");
        }
    }
}
