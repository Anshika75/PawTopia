package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class TheShadowedIsleStory extends Story {

    public TheShadowedIsleStory() {
        super("The Shadowed Isle", 5, 150,
                "A sailor approaches you and your pet with tales of an uncharted isle, shrouded in mist and mystery. They claim a great treasure lies hidden there. Do you and your pet embark on this perilous journey?",
                300, 5); // Base reward of 300 bank balance and 5 game level increment
    }

    @Override
    public boolean playStory(Pet pet) {
        System.out.println("\n" + getDescription());
        System.out.println("Your pet " + pet.getName() + " seems curious but slightly apprehensive.");
        System.out.println("1. Board the ship and set sail.");
        System.out.println("2. Decline the offer and stay safe.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nYou and " + pet.getName() + " board the ship, ready for an adventure!");             return exploreIsle(pet);
        } else {
            System.out.println("\nYou decide to stay behind. " + pet.getName() + " seems relieved, but the sailor glares at you with suspicion.");             return false;
        }
    }

    private boolean exploreIsle(Pet pet) {
        System.out.println("\nAfter days at sea, the ship reaches a shadowy isle surrounded by jagged rocks.");
        System.out.println("As you step ashore, strange symbols glow faintly on the sand. " + pet.getName() + " " + pet.getVoice() + "s softly.");
        System.out.println("Do you investigate the symbols or proceed deeper into the jungle?");
        System.out.println("1. Examine the glowing symbols.");
        System.out.println("2. Ignore the symbols and head into the jungle.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {             return investigateSymbols(pet);
        } else {             return enterJungle(pet);
        }
    }

    private boolean investigateSymbols(Pet pet) {
        System.out.println("\nYou crouch down to examine the symbols. Suddenly, the ground trembles!");
        System.out.println(pet.getName() + " " + pet.getVoice() + "s and nudges you away just as a hidden trapdoor opens.");
        System.out.println("Do you:");
        System.out.println("1. Descend into the trapdoor.");
        System.out.println("2. Retreat and head into the jungle instead.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {             return exploreUnderground(pet);
        } else {             return enterJungle(pet);
        }
    }

    private boolean enterJungle(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + " venture into the thick jungle. The air is heavy with the scent of flowers, but it feels too quiet.");
        System.out.println("You come across an ancient statue holding a gemstone. " + pet.getName() + " " + pet.getVoice() + "s, sensing danger.");
        System.out.println("Do you:");
        System.out.println("1. Take the gemstone.");
        System.out.println("2. Leave it and continue deeper into the jungle.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nThe moment you touch the gemstone, the statue begins to move!");
            System.out.println(pet.getName() + " " + pet.getVoice() + "s furiously, helping you dodge the statue's attacks.");
            System.out.println("You manage to escape with the gemstone, but your adventure continues.");
            updatePetReward(pet, 150, 2);             return encounterRuins(pet);
        } else {
            System.out.println("\nYou wisely leave the gemstone and proceed deeper. " + pet.getName() + " seems relieved.");             return encounterRuins(pet);
        }
    }

    private boolean exploreUnderground(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + " descend into a network of tunnels illuminated by bioluminescent plants.");
        System.out.println("You find an inscription hinting at two paths: 'The Way of Trials' and 'The Way of Silence.'");
        System.out.println("Do you:");
        System.out.println("1. Take the Way of Trials (dangerous but rewarding).");
        System.out.println("2. Take the Way of Silence (safe but slow).\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nYou face traps, puzzles, and strange creatures, but " + pet.getName() + " proves invaluable.");
            System.out.println("Together, you overcome the challenges and find a hidden cache of treasure!");             updatePetReward(pet, 300, 4);
        } else {
            System.out.println("\nThe silent path is eerie, but it leads you safely to an ancient treasure chamber.");             updatePetReward(pet, 200, 2);
        }

        return true;
    }

    private boolean encounterRuins(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + " discover the ruins of an ancient temple.");
        System.out.println("Inside, you find a pedestal holding an ornate chest, but the room is filled with shifting shadows.");
        System.out.println("Do you:");
        System.out.println("1. Approach the chest carefully.");
        System.out.println("2. Leave the temple and search elsewhere.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nAs you reach for the chest, shadowy figures emerge! " + pet.getName() + " defends you valiantly.");
            System.out.println("You manage to open the chest and claim the treasure before escaping.");             updatePetReward(pet, 400, 5);
        } else {
            System.out.println("\nYou leave the temple, but " + pet.getName() + " " + pet.getVoice() + "s insistently, leading you to another hidden cache of treasure.");             updatePetReward(pet, 250, 3);
        }

        return true;
    }

    // Function to update the pet's reward directly without creating Reward objects
    private void updatePetReward(Pet pet, int balance, int levelIncrement) {
        pet.setBankBalance(pet.getBankBalance() + balance);
        pet.setGameLevel(pet.getGameLevel() + levelIncrement);
        System.out.println("\nYour pet " + pet.getName() + " helped you earn " + balance + " coins!");
        System.out.println("Your game level increased by " + levelIncrement + "! " + pet.getName() + " looks proud of its contribution.\n");
    }
}
