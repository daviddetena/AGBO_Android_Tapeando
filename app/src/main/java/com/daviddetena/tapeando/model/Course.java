package com.daviddetena.tapeando.model;

import java.util.List;

public class Course {

    private int mId;
    private String mName;
    private String mPhoto;
    private String mPhotoUrl;
    private String mDescription;
    private List<Allergen> mAllergens;
    private float mPrice;
    private String mNotes;

    public Course() {
        mId = 1;
        mName = "Plato de ejemplo";
        mPrice = 10.50f;
        mDescription = "Descripción de ejemplo";
    }

    public Course(int id, String name, String photo, String photoUrl, String description,
                  List<Allergen> allergens, float price, String notes) {
        mId = id;
        mName = name;
        mPhoto = photo;
        mPhotoUrl = photoUrl;
        mDescription = description;
        mAllergens = allergens;
        mPrice = price;
        mNotes = notes;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public List<Allergen> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        mAllergens = allergens;
    }
}
