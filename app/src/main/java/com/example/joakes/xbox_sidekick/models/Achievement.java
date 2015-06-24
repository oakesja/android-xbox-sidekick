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
    private boolean isSecret;
    private String description;
    private String lockedDescription;
    private int value;
    private String iconUrl;
    private boolean isLocked;
    private GregorianCalendar timeUnlocked;

    public Achievement(long id, String name, boolean isSecret, String description,
                       String lockedDescription, int value, String iconUrl,
                       boolean isLocked, GregorianCalendar timeUnlocked) {
        this.id = id;
        this.name = name;
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

    public GregorianCalendar getTimeUnlocked() {
        return timeUnlocked;
    }

    public void setTimeUnlocked(GregorianCalendar timeUnlocked) {
        this.timeUnlocked = timeUnlocked;
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
        if (getIconUrl() != null ? !getIconUrl().equals(that.getIconUrl()) : that.getIconUrl() != null)
            return false;
        return !(getTimeUnlocked() != null ? !getTimeUnlocked().equals(that.getTimeUnlocked()) : that.getTimeUnlocked() != null);

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
        result = 31 * result + (getTimeUnlocked() != null ? getTimeUnlocked().hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(lockedDescription);
        parcel.writeInt(value);
        parcel.writeString(iconUrl);
        parcel.writeInt((byte) (isLocked ? 1 : 0));
        parcel.writeInt((byte) (isSecret ? 1 : 0));
        if(timeUnlocked != null){
            parcel.writeLong(timeUnlocked.getTimeInMillis());
        }
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
