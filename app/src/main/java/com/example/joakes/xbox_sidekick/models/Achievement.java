package com.example.joakes.xbox_sidekick.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.GregorianCalendar;

/**
 * Created by joakes on 6/4/15.
 */
public class Achievement implements Parcelable {
    private long id;
    private String name;
    private String gameName;
    private boolean isSecret;
    private String description;
    private String lockedDescription;
    private int value;
    private String iconUrl;
    private boolean isLocked;
    private GregorianCalendar timeUnlocked;

    public Achievement(long id, String name, String gameName, boolean isSecret, String description,
                       String lockedDescription, int value, String iconUrl,
                       boolean isLocked, GregorianCalendar timeUnlocked) {
        this.id = id;
        this.name = name;
        this.gameName = gameName;
        this.isSecret = isSecret;
        this.description = description;
        this.lockedDescription = lockedDescription;
        this.value = value;
        this.iconUrl = iconUrl;
        this.isLocked = isLocked;
        this.timeUnlocked = timeUnlocked;
    }

    protected Achievement(Parcel in) {
        id = in.readLong();
        name = in.readString();
        gameName = in.readString();
        description = in.readString();
        lockedDescription = in.readString();
        value = in.readInt();
        iconUrl = in.readString();
        isLocked = in.readInt() != 0;
        isSecret = in.readInt() != 0;
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(in.readLong());
        timeUnlocked = calendar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(gameName);
        parcel.writeString(description);
        parcel.writeString(lockedDescription);
        parcel.writeInt(value);
        parcel.writeString(iconUrl);
        parcel.writeInt((byte) (isLocked ? 1 : 0));
        parcel.writeInt((byte) (isSecret ? 1 : 0));
        if (timeUnlocked != null) {
            parcel.writeLong(timeUnlocked.getTimeInMillis());
        }
    }

    public static final Creator<Achievement> CREATOR = new Creator<Achievement>() {
        @Override
        public Achievement createFromParcel(Parcel in) {
            return new Achievement(in);
        }

        @Override
        public Achievement[] newArray(int size) {
            return new Achievement[size];
        }
    };

    // TODO: remove all setters use a builder instead in test setup
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setIsSecret(boolean isSecret) {
        this.isSecret = isSecret;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLockedDescription(String lockedDescription) {
        this.lockedDescription = lockedDescription;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void setTimeUnlocked(GregorianCalendar timeUnlocked) {
        this.timeUnlocked = timeUnlocked;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGameName() {
        return gameName;
    }

    public boolean isSecret() {
        return isSecret;
    }

    public String getDescription() {
        return description;
    }

    public String getLockedDescription() {
        return lockedDescription;
    }

    public int getValue() {
        return value;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public GregorianCalendar getTimeUnlocked() {
        return timeUnlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (id != that.id) return false;
        if (isSecret != that.isSecret) return false;
        if (value != that.value) return false;
        if (isLocked != that.isLocked) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gameName != null ? !gameName.equals(that.gameName) : that.gameName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (lockedDescription != null ? !lockedDescription.equals(that.lockedDescription) : that.lockedDescription != null)
            return false;
        if (iconUrl != null ? !iconUrl.equals(that.iconUrl) : that.iconUrl != null) return false;
        return !(timeUnlocked != null ? !timeUnlocked.equals(that.timeUnlocked) : that.timeUnlocked != null);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (isSecret ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lockedDescription != null ? lockedDescription.hashCode() : 0);
        result = 31 * result + value;
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (isLocked ? 1 : 0);
        result = 31 * result + (timeUnlocked != null ? timeUnlocked.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gameName='" + gameName + '\'' +
                ", isSecret=" + isSecret +
                ", description='" + description + '\'' +
                ", lockedDescription='" + lockedDescription + '\'' +
                ", value=" + value +
                ", iconUrl='" + iconUrl + '\'' +
                ", isLocked=" + isLocked +
                ", timeUnlocked=" + timeUnlocked +
                '}';
    }
}
