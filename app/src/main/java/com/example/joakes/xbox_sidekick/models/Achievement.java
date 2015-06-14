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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (getId() != that.getId()) return false;
        if (isSecret() != that.isSecret()) return false;
        if (getValue() != that.getValue()) return false;
        if (isLocked() != that.isLocked()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getLockedDescription() != null ? !getLockedDescription().equals(that.getLockedDescription()) : that.getLockedDescription() != null)
            return false;
        return !(getIconUrl() != null ? !getIconUrl().equals(that.getIconUrl()) : that.getIconUrl() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (isSecret() ? 1 : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLockedDescription() != null ? getLockedDescription().hashCode() : 0);
        result = 31 * result + getValue();
        result = 31 * result + (getIconUrl() != null ? getIconUrl().hashCode() : 0);
        result = 31 * result + (isLocked() ? 1 : 0);
        return result;
    }
}
