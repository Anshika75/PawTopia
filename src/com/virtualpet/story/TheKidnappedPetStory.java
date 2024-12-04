package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class TheKidnappedPetStory extends Story {

    public TheKidnappedPetStory() {
        super("The Kidnapped Pet", 10, 300,
                "One evening, while you and your pet are out on a walk, a mysterious figure abducts a friend of yours. They leave behind a cryptic note with a threat: 'If you want to see them again, find the hidden clues.' Do you embark on this dangerous rescue mission?",
                500, 10); // Base reward of 500 bank balance and 5 game level increment
    }

    @Override
    public boolean playStory(Pet pet) {
        System.out.println("\n" + getDescription());
        System.out.println("Your pet " + pet.getName() + " is growling, clearly upset.");
        System.out.println("1. Follow the trail immediately and search for clues.");
        System.out.println("2. Call the authorities and wait for their help.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nYou and " + pet.getName() + " decide to take action, setting off to follow the trail.");
             ;
            return followTrail(pet);
        } else {
            System.out.println("\nYou decide to wait for help, but the time spent waiting only deepens the feeling of dread.");
             ;
            return false;
        }
    }

    private boolean followTrail(Pet pet) {
        System.out.println("\nThe trail leads you through a dark alley, where you find the first clue: a piece of fabric from a torn jacket.");
        System.out.println(pet.getName() + " sniffs the fabric and pulls you toward a hidden door under a staircase.");
        System.out.println("Do you:");
        System.out.println("1. Open the door and go down into the underground.");
        System.out.println("2. Knock on the door and demand answers.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return exploreUnderground(pet);
        } else {
             ;
            return confrontKidnapper(pet);
        }
    }

    private boolean exploreUnderground(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + " descend into a dark, musty tunnel.");
        System.out.println("The air is thick, and strange noises echo through the passage .Suddenly, a shadowy figure appears, holding a rope.");
        System.out.println("Do you:");
        System.out.println("1. Approach the figure cautiously.");
        System.out.println("2. Hide and observe their next move.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return faceKidnapper(pet);
        } else {
             ;
            return gatherInformation(pet);
        }
    }

    private boolean faceKidnapper(Pet pet) {
        System.out.println("\nThe figure turns, revealing a cold, emotionless face.");
        System.out.println("They demand to know what you're doing here, and offer you a choice: ‘Leave or face consequences.’");
        System.out.println("Do you:");
        System.out.println("1. Threaten them with your pet's strength and refuse to back down.");
        System.out.println("2. Try to convince them that you're here to help.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\nYour pet " + pet.getName() + " growls fiercely, scaring the kidnapper into revealing the truth.");
            System.out.println("They tell you where your friend is held—an old abandoned warehouse by the docks.");
             ;
            return rescueFriend(pet);
        } else {
            System.out.println("\nThe kidnapper scoffs at your pleas. They throw a smoke bomb, vanishing in a cloud of smoke.");
            System.out.println("You and " + pet.getName() + " are left alone in the darkness, frustrated.");
             ;
            return false;
        }
    }

    private boolean gatherInformation(Pet pet) {
        System.out.println("\nYou quietly observe the figure, and after a while, you notice them pulling out a map with marked locations.");
        System.out.println("You quietly memorize the route, and decide to follow them silently.");
        return rescueFriend(pet);
    }

    private boolean confrontKidnapper(Pet pet) {
        System.out.println("\nYou knock firmly on the door, and the kidnapper answers, looking annoyed.");
        System.out.println("They demand you leave but seem to hesitate when they see the determination in your eyes.");
        System.out.println("Do you:");
        System.out.println("1. Push past them and enter the hideout.");
        System.out.println("2. Try to bargain with them, offering them a deal in exchange for your friend’s safety.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return rescueFriend(pet);
        } else {
            System.out.println("\nThe kidnapper scoffs and threatens to harm your friend. This only fuels your determination to proceed.");
             ;
            return rescueFriend(pet);
        }
    }

    private boolean rescueFriend(Pet pet) {
        System.out.println("\nYou arrive at the abandoned warehouse. The door is ajar, and faint voices can be heard inside.");
        System.out.println("Your pet " + pet.getName() + " begins to whine softly, sensing the danger.");
        System.out.println("Do you:");
        System.out.println("1. Rush in, using surprise to your advantage.");
        System.out.println("2. Wait and observe, looking for weaknesses in the kidnapper’s defenses.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return fightKidnapper(pet);
        } else {
             ;
            return strategize(pet);
        }
    }

    private boolean fightKidnapper(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + " burst into the warehouse, catching the kidnapper off guard.");
        System.out.println("After a brief struggle, you overpower them, freeing your friend from the ropes.");
        updatePetReward(pet, 500, 4);
        return true;
    }

    private boolean strategize(Pet pet) {
        System.out.println("\nYou carefully observe the kidnapper’s movements, waiting for the perfect moment to strike.");
        System.out.println("Your patience pays off when the kidnapper momentarily leaves the room, allowing you to sneak in and free your friend.");
        updatePetReward(pet, 750, 3);
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
