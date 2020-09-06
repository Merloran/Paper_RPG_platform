package com.macko;

public class Race implements Comparable<Race> {
    private String name, description=" ";
    private int strength=0, endurance=0, agility=0, intelligence=0, luck=0, charisma=0;

    public Race(String name, String description, int strength, int endurance, int agility, int intelligence, int luck, int charisma) {
        this.name = name;
        this.description = description;
        this.strength = strength;
        this.endurance = endurance;
        this.agility = agility;
        this.intelligence = intelligence;
        this.luck = luck;
        this.charisma = charisma;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStrength() {
        return strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getLuck() {
        return luck;
    }

    public int getCharisma() {
        return charisma;
    }

    @Override
    public int compareTo(Race o) {
        return this.getName().compareTo(o.getName());
    }
}
