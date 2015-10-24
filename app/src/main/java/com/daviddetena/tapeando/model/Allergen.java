package com.daviddetena.tapeando.model;

public class Allergen {

    private String mName;
    private String mIcon;

    public Allergen(String icon, String name) {
        mIcon = icon;
        mName = name;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
