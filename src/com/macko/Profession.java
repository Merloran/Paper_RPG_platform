package com.macko;

import java.util.List;

public class Profession implements Comparable<Profession> {
    private String name, description=" ";
    private List<Talent> talents;

    public Profession(String name, String description, List<Talent> talents) {
        this.name = name;
        this.description = description;
        this.talents = talents;
        for (Talent talent : this.talents) talent.setTier(1);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    @Override
    public int compareTo(Profession o) {
        return this.getName().compareTo(o.getName());
    }
}
