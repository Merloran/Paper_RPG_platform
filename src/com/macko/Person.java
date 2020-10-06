package com.macko;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person implements Comparable<Person>  {
    private int strength, endurance, agility, intelligence, luck, charisma;
    private String name, title, nation, defence="0", damage="0";
    private Profession profession;
    private Race race;
    private int health, healthMax, condition, conditionMax, mana, manaMax, fate, capacity, gold, age, statistic=0, talent=0;
    private List<Talent> talents;
    private List<Item> items = new ArrayList<>(), equippedItems = new ArrayList<>();

    public Person(String name, String title, String nation, Profession profession, Race race) {
        this.name = name;
        this.title = title;
        this.nation = nation;
        this.profession = profession;
        this.race = race;

        final int minimumStatsValue = 3;
        this.age = random_value(45)+16;
        this.strength = random_value()+minimumStatsValue;
        this.endurance = random_value()+minimumStatsValue;
        this.agility = random_value()+minimumStatsValue;
        this.intelligence = random_value()+minimumStatsValue;
        this.luck = random_value()+minimumStatsValue;
        this.charisma = random_value()+minimumStatsValue;
    }

    void update_statistics(){
        this.strength += race.getStrength();
        this.endurance += race.getEndurance();
        this.agility += race.getAgility();
        this.intelligence += race.getIntelligence();
        this.luck += race.getLuck();
        this.charisma += race.getCharisma();
    }

    void update_values(List<Talent> talents){
        this.healthMax = this.endurance*10+70;
        this.health = this.healthMax;

        this.conditionMax = this.agility*5+35;
        this.condition = this.conditionMax;

        this.manaMax = this.intelligence*10+70;
        this.mana = this.manaMax;

        this.fate = this.luck*2;

        this.gold = this.charisma*5+15;

        this.capacity = this.strength/4+1;

        this.talents = talents;
    }

    int random_value(){
        Random random = new Random();
        return random.nextInt(16);
    }

    int random_value(int value){
        Random random = new Random();
        return random.nextInt(value);
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

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getNation() {
        return nation;
    }

    public String getDefence() {
        return defence;
    }

    public String getDamage() {
        return damage;
    }

    public Profession getProfession() {
        return profession;
    }

    public Race getRace() {
        return race;
    }

    public int getHealth() {
        return health;
    }

    public int getHealthMax() {
        return healthMax;
    }

    public int getCondition() {
        return condition;
    }

    public int getConditionMax() {
        return conditionMax;
    }

    public int getMana() {
        return mana;
    }

    public int getManaMax() {
        return manaMax;
    }

    public int getFate() {
        return fate;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getGold() {
        return gold;
    }

    public int getAge() {
        return age;
    }

    public int getStatistic() {
        return statistic;
    }

    public int getTalent() {
        return talent;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    @Override
    public int compareTo(Person o) {
        return this.getName().compareTo(o.getName());
    }
}
