package com.example.laptop.metronome.items;

/**
 * Created by laptop on 20.02.2015.
 */
public class Tempo {

    private int id;
    private boolean enabled;
    private String name;
    private int topLevelTick;
    private int bottomLevelTick;

    public Tempo(int id, String name, int bottomLevelTick, int topLevelTick)
    {
        this.id = id;
        this.name = name;
        this.bottomLevelTick = bottomLevelTick;
        this.topLevelTick = topLevelTick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopLevelTick() {
        return topLevelTick;
    }

    public void setTopLevelTick(int topLevelTick) {
        this.topLevelTick = topLevelTick;
    }

    public int getBottomLevelTick() {
        return bottomLevelTick;
    }

    public void setBottomLevelTick(int bottomLevelTick) {
        this.bottomLevelTick = bottomLevelTick;
    }
}
