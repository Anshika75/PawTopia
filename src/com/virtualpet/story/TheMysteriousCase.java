package com.virtualpet.story;

import com.virtualpet.pet.Pet;

import java.util.Scanner;

public class TheMysteriousCase extends Story {

    public TheMysteriousCase() {
        super("The Mysterious Case", 15, 500,
                "One quiet evening, your pet alerts you to a strange disturbance near the outskirts of town. A series of bizarre disappearances have plagued the area, and you receive a cryptic letter urging you to investigate further. Will you take on the investigation and uncover the hidden truth behind these disappearances?",
                700, 15); // Base reward of 700 bank balance and 15 game level increment
    }

    @Override
    public boolean playStory(Pet pet) {
        System.out.println("\n" + getDescription());
        System.out.println("Your pet " + pet.getName() + " is visibly uneasy, its senses heightened.");
        System.out.println("1. Head to the town outskirts to investigate the source of the disturbances.");
        System.out.println("2. Call the authorities and wait for their investigation to proceed.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println(
                    "\nYou and " + pet.getName() + " decide to take action and head to the outskirts of town.");
             ;
            return investigateArea(pet);
        } else {
            System.out.println(
                    "\nYou decide to wait for authorities, but the increasing sense of dread suggests something sinister is at play.");
             ;
            return false;
        }

    }

    private boolean investigateArea(Pet pet) {
        System.out.println(
                "\nUpon reaching the outskirts, you notice a series of strange markings on nearby trees, almost like a trail.");
        System.out.println(pet.getName() + " begins sniffing around, its instincts kicking in.");
        System.out.println("Do you:");
        System.out.println("1. Follow the trail of strange markings deeper into the forest.");
        System.out.println("2. Investigate the nearby abandoned house that seems to be a source of the activity.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return followTrail(pet);
        } else {
             ;
            return exploreHouse(pet);
        }
    }

    private boolean followTrail(Pet pet) {
        System.out.println(
                "\nThe markings lead you into the heart of a dense forest, where the atmosphere grows darker and more oppressive.");
        System.out.println("Suddenly, you hear rustling in the bushes—something is moving close by.");
        System.out.println("Do you:");
        System.out.println("1. Investigate the noise cautiously.");
        System.out.println("2. Hide and wait for the noise to stop.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return encounterMystery(pet);
        } else {
             ;
            return gatherInformation(pet);
        }
    }

    private boolean encounterMystery(Pet pet) {
        System.out.println(
                "\nAs you approach, a shadowy figure emerges from the darkness. The figure appears to be searching for something.");
        System.out.println(
                "They notice you, but instead of attacking, they offer you a deal: ‘Help me, and I’ll reveal everything I know.’");
        System.out.println("Do you:");
        System.out.println("1. Accept the offer and help them find what they are looking for.");
        System.out.println("2. Refuse and demand answers on the spot.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return assistMysteriousFigure(pet);
        } else {
             ;
            return demandAnswers(pet);
        }
    }

    private boolean assistMysteriousFigure(Pet pet) {
        System.out.println("\nThe figure leads you to an old, overgrown shrine deep within the forest.");
        System.out.println("As you begin to help them search, your pet, " + pet.getName()
                + ", starts barking loudly, sensing danger.");
        System.out.println("Do you:");
        System.out.println("1. Ignore the warning and continue the search.");
        System.out.println("2. Heed the warning and prepare for something unexpected.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return uncoverDarkSecret(pet);
        } else {
             ;
            return prepareForConfrontation(pet);
        }
    }

    private boolean uncoverDarkSecret(Pet pet) {
        System.out.println(
                "\nAs you search the shrine, you uncover an ancient artifact, but the figure suddenly attacks!");
        System.out.println("They have been manipulating you all along, leading you into a trap.");
        System.out.println("Your pet " + pet.getName() + " leaps into action, defending you fiercely.");
        return confrontAttacker(pet);
    }

    private boolean confrontAttacker(Pet pet) {
        System.out.println(
                "\nWith your pet's help, you overpower the figure. They reveal themselves as part of a larger cult responsible for the disappearances.");
        System.out.println("Before they are captured, they tell you where to find the cult's hideout.");
        return cultHideout(pet);
    }

    private boolean prepareForConfrontation(Pet pet) {
        System.out.println("\nYou prepare yourself, and soon, a group of cult members appears, surrounding you.");
        System.out.println("Your pet " + pet.getName() + " stands bravely at your side, ready for the fight.");
        return cultHideout(pet);
    }

    private boolean cultHideout(Pet pet) {
        System.out.println("\nYou find the cult's hideout, hidden beneath a crumbling mansion at the edge of town.");
        System.out.println(
                "Inside, you see evidence of dark rituals and otherworldly symbols. The cultists are deep in their ceremony.");
        System.out.println("Do you:");
        System.out.println("1. Interrupt the ritual and confront the cult leader.");
        System.out.println("2. Try to sneak around, looking for any survivors or clues.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return interruptRitual(pet);
        } else {
             ;
            return findSurvivors(pet);
        }
    }

    private boolean interruptRitual(Pet pet) {
        System.out.println("\nYou and " + pet.getName() + "storm into the cult's lair, surprising the cultists.");
        System.out.println("The cult leader challenges you, but your pet is quick to pounce, preventing their escape.");
        updatePetReward(pet, 800, 10);
        return true;
    }

    private boolean findSurvivors(Pet pet) {
        System.out.println(
                "\nYou quietly search the hideout and find several people who have been imprisoned, awaiting ritual sacrifice.");
        System.out.println("With your pet's help, you free them and uncover the cult’s plans.");
        updatePetReward(pet, 700, 8);
        return true;
    }

    private boolean exploreHouse(Pet pet) {
        System.out.println("\nThe abandoned house seems to be empty, but there’s an eerie feeling in the air.");
        System.out.println(
                "You discover several hidden rooms, one of which contains a series of photographs depicting the missing persons.");
        System.out.println("Suddenly, the door slams shut, and you hear footsteps approaching.");
        System.out.println("Do you:");
        System.out.println("1. Hide and wait for the footsteps to pass.");
        System.out.println("2. Confront whoever is approaching.\n");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice == 1) {
             ;
            return gatherMoreClues(pet);
        } else {
             ;
            return faceTheIntruder(pet);
        }
    }

    private boolean gatherMoreClues(Pet pet) {
        System.out.println(
                "\nYou quietly observe the intruder, discovering that they are looking for something you found earlier.");
        System.out.println("The intruder notices you, but you manage to escape, taking the clues with you.");
        return investigateFinalClue(pet);
    }

    private boolean faceTheIntruder(Pet pet) {
        System.out.println("\nYou confront the intruder, and after a brief struggle, you manage to overpower them.");
        System.out.println(
                "They reveal themselves as a member of the same cult, and offer to cooperate in exchange for their life.");
        return cultHideout(pet);
    }

    private boolean investigateFinalClue(Pet pet) {
        System.out.println(
                "\nThe final clue leads you to a hidden location where the cult keeps its victims, preparing for dark rituals.");
        System.out.println("You and " + pet.getName() + "must now confront the full scope of the mystery.");
        updatePetReward(pet, 900, 15);
        return true;
    }

    private boolean demandAnswers(Pet pet) {
        System.out.println("\nThe shadowy figure scoffs at your demand, but seems reluctant to fight.");
        System.out.println(
                "They reveal that the disappearances are part of a much larger plan, and they hint at something even more sinister.");
        return cultHideout(pet);
    }

    private boolean gatherInformation(Pet pet) {
        System.out.println(
                "\nYou decide to wait and watch the figure, learning that they are searching for an important item hidden nearby.");
        System.out.println("After some time, they lead you to a secret meeting spot.");
        return investigateArea(pet);
    }

    // Function to update the pet's reward directly without creating Reward objects
    private void updatePetReward(Pet pet, int balance, int levelIncrement) {
        pet.setBankBalance(pet.getBankBalance() + balance);
        pet.setGameLevel(pet.getGameLevel() + levelIncrement);
        System.out.println("\nYour pet " + pet.getName() + " helped you earn " + balance + " coins!");
        System.out.println("Your game level increased by " + levelIncrement + "! " + pet.getName()
                + " looks proud of its contribution.\n");
    }

}
