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
        boolean canProceed = true; // Start optimistic
        StringBuilder warnings = new StringBuilder();
    
        // Check each metric and generate warnings if thresholds are breached
        if (food + activity.getFoodChange() <= MIN_THRESHOLD) {
            if (food > 0) warnings.append("Warning: Your pet is hungry and needs food.\n");
            if (food + activity.getFoodChange() <= 0) canProceed = false;
        }
        if (energy + activity.getEnergyChange() <= MIN_THRESHOLD) {
            if (energy > 0) warnings.append("Warning: Your pet is too tired.\n");
            if (energy + activity.getEnergyChange() <= 0) canProceed = false;
        }
        if (bath + activity.getBathChange() <= MIN_THRESHOLD) {
            if (bath > 0) warnings.append("Warning: Your pet needs a bath to feel refreshed.\n");
            if (bath + activity.getBathChange() <= 0) canProceed = false;
        }
        if (sleep + activity.getSleepChange() <= MIN_THRESHOLD) {
            if (sleep > 0) warnings.append("Warning: Your pet needs sleep.\n");
            if (sleep + activity.getSleepChange() <= 0) canProceed = false;
        }
        if (play + activity.getPlayChange() <= MIN_THRESHOLD) {
            if (play > 0) warnings.append("Warning: Your pet feels lonely and wants to play.\n");
            if (play + activity.getPlayChange() <= 0) canProceed = false;
        }
        if (happiness + activity.getHappinessChange() <= MIN_THRESHOLD) {
            if (happiness > 0) warnings.append("Warning: Your pet is unhappy and needs cheering up.\n");
            if (happiness + activity.getHappinessChange() <= 0) canProceed = false;
        }
    
        // Print warnings, if any
        if (warnings.length() > 0) {
            System.out.println(warnings.toString());
        }
    
        return canProceed; // Allow activity only if no parameter hits zero
    }
    
}

