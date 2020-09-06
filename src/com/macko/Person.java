package com.macko;

import java.util.ArrayList;
import java.util.List;

public class Person implements Comparable<Person>  {
    int strength, endurance, agility, intelligence, luck, charisma;
    String name, title, nation, defence, damage;
    Profession profession;
    Race race;
    int health, healthMax, condition, conditionMax, mana, manaMax, fate, capacity, gold, age, statistic, talent;
    List<Talent> talents;
    List<Item> items = new ArrayList<>(), equippedItems = new ArrayList<>();

    public Person(int strength, int endurance, int agility, int intelligence, int luck, int charisma, String name, String title, String nation, String defence, String damage, Profession profession, Race race, int age, List<Talent> talents) {
        this.strength = strength;
        this.endurance = endurance;
        this.agility = agility;
        this.intelligence = intelligence;
        this.luck = luck;
        this.charisma = charisma;
        this.name = name;
        this.title = title;
        this.nation = nation;
        this.defence = defence;
        this.damage = damage;
        this.profession = profession;
        this.race = race;
        this.age = age;
        this.talents = talents;
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
