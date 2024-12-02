package com.virtualpet.activity;

public enum Activity {
    SLEEP(-10, +20, -10, +50, -20, +5, +1),
    EATING(+50, +30, -20, -10, -10, +5, +1),
    BATHING(-10, -10, +30, -10, +5, +10, +2),
    PLAYING(-20, -20, -20, -20, +50, +15, +3);

    private final int foodChange;
    private final int energyChange;
    private final int bathChange;
    private final int sleepChange;
    private final int playChange;
    private final int happinessChange;
    private final int gameLevelChange;

    // Define thresholds
    private static final int MIN_THRESHOLD = 10;

    Activity(int foodChange, int energyChange, int bathChange, int sleepChange, int playChange, int happinessChange, int gameLevelChange) {
        this.foodChange = foodChange;
        this.energyChange = energyChange;
        this.bathChange = bathChange;
        this.sleepChange = sleepChange;
        this.playChange = playChange;
        this.happinessChange = happinessChange;
        this.gameLevelChange = gameLevelChange;
    }

    public int getFoodChange() { return foodChange; }
    public int getEnergyChange() { return energyChange; }
    public int getBathChange() { return bathChange; }
    public int getSleepChange() { return sleepChange; }
    public int getPlayChange() { return playChange; }
    public int getHappinessChange() { return happinessChange; }
    public int getGameLevelChange() { return gameLevelChange; }

    public static boolean canPerformActivity(int food, int energy, int bath, int sleep, int play, int happiness, Activity activity) {
        // Check each metric against its threshold
        if (food + activity.getFoodChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet is hungry and needs food.");
            return food + activity.getFoodChange() > 0; // Block if it would fall below zero
        }
        if (energy + activity.getEnergyChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet is too tired.");
            return energy + activity.getEnergyChange() > 0;
        }
        if (bath + activity.getBathChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet needs a bath to feel refreshed.");
            return bath + activity.getBathChange() > 0;
        }
        if (sleep + activity.getSleepChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet needs sleep.");
            return sleep + activity.getSleepChange() > 0;
        }
        if (play + activity.getPlayChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet feels lonely and wants to play.");
            return play + activity.getPlayChange() > 0;
        }
        if (happiness + activity.getHappinessChange() < MIN_THRESHOLD) {
            System.out.println("Warning: Your pet is unhappy and needs cheering up.");
            return happiness + activity.getHappinessChange() > 0;
        }
        return true; // All thresholds met
    }
}
