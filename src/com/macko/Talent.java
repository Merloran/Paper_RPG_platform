package com.macko;

public class Talent implements Comparable<Talent> {
    private String name;
    private int tier, statistic;

    public Talent(String name, int tier, int statistic) {
        this.name = name;
        this.tier = tier;
        this.statistic = statistic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getStatistic() {
        return statistic;
    }

    public void setStatistic(int statistic) {
        this.statistic = statistic;
    }

    @Override
    public int compareTo(Talent o) {
        return this.getName().compareTo(o.getName());
    }
}
