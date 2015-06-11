package com.example.joakes.xbox_sidekick.models;

/**
 * Created by joakes on 6/4/15.
 */
public class Achievement {
    private long id;
    private String name;
    private boolean isSecret;
    private String description;
    private String lockedDescription;
    private int value;
    private String iconUrl;
    private boolean isLocked;

    public Achievement(long id, String name, boolean isSecret, String description, String lockedDescription, int value, String iconUrl, boolean isLocked) {
        this.id = id;
        this.name = name;
        this.isSecret = isSecret;
        this.description = description;
        this.lockedDescription = lockedDescription;
        this.value = value;
        this.iconUrl = iconUrl;
        this.isLocked = isLocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSecret() {
        return isSecret;
    }

    public void setIsSecret(boolean isSecret) {
        this.isSecret = isSecret;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLockedDescription() {
        return lockedDescription;
    }

    public void setLockedDescription(String lockedDescription) {
        this.lockedDescription = lockedDescription;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
