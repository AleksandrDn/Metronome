package com.example.laptop.metronome.items;

/**
 * Created by laptop on 20.02.2015.
 */
public class Sound {

    private int id;
    private int resourceId;
    private boolean enabled = false;

    public Sound(int id, int resourceId)
    {
        this.id = id;
        this.resourceId = resourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
