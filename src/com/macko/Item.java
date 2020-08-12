package com.macko;

public class Item implements Comparable<Item> {
    private String name="", description="", damage="", defence="";
    private int health=0, condition=0, mana=0, strength=0, endurance=0, agility=0, intelligence=0, luck=0, charisma=0, mass=0;

    public Item(String name, int health, int condition, int mana, int strength, int endurance, int agility, int intelligence, int luck, int charisma, int mass, String damage, String defence, String description) {
        this.name = name;
        this.health = health;
        this.condition = condition;
        this.mana = mana;
        this.strength = strength;
        this.endurance = endurance;
        this.agility = agility;
        this.intelligence = intelligence;
        this.luck = luck;
        this.charisma = charisma;
        this.mass = mass;
        this.damage = damage;
        this.defence = defence;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDefence() {
        return defence;
    }

    public void setDefence(String defence) {
        this.defence = defence;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public int compareTo(Item o) {
        return this.getName().compareTo(o.getName());
    }
}
