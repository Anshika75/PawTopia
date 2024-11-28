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

    Activity(int foodChange, int energyChange, int bathChange, int sleepChange, int playChange, int happinessChange, int gameLevelChange) {
        this.foodChange = foodChange;
        this.energyChange = energyChange;
        this.bathChange = bathChange;
        this.sleepChange = sleepChange;
        this.playChange = playChange;
        this.happinessChange = happinessChange;
        this.gameLevelChange = gameLevelChange;
    }

    // Getters for activity effects
    public int getFoodChange() {
        return foodChange;
    }

    public int getEnergyChange() {
        return energyChange;
    }

    public int getBathChange() {
        return bathChange;
    }

    public int getSleepChange() {
        return sleepChange;
    }

    public int getPlayChange() {
        return playChange;
    }

    public int getHappinessChange() {
        return happinessChange;
    }

    public int getGameLevelChange() {
        return gameLevelChange;
    }
}
