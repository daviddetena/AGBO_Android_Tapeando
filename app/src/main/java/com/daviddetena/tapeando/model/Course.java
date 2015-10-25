package com.daviddetena.tapeando.model;

import com.daviddetena.tapeando.R;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int mId;
    private int mTableNumber;
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

    public Course(int id, int tableNumber, String name, float price, String notes){
        mId = id;
        mTableNumber = tableNumber;
        mName = name;
        mPrice = price;
        mNotes = notes;
        setDummyAllergens();
    }

    private void setDummyAllergens() {

        Allergen frutosSecos = new Allergen("frutos_secos", "Frutos Secos");
        Allergen mostaza = new Allergen("mostaza", "Mostaza");
        Allergen gluten = new Allergen("gluten", "Gluten");
        Allergen pescado = new Allergen("pescado", "Pescado");
        Allergen marisco = new Allergen("marisco", "Marisco");
        Allergen huevo = new Allergen("huevo", "Huevo");
        Allergen lactosa = new Allergen("lactosa", "Lactosa");

        List<Allergen> allergens = new ArrayList<>();
        allergens.add(frutosSecos);
        allergens.add(mostaza);
        allergens.add(gluten);
        allergens.add(pescado);
        allergens.add(marisco);
        allergens.add(huevo);
        allergens.add(lactosa);

        setAllergens(allergens);
    }

    public Course(int id, int tableNumber, String name, String photo, String photoUrl, String description,
                  List<Allergen> allergens, float price, String notes) {
        mId = id;
        mTableNumber = tableNumber;
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

    public int getTableNumber() {
        return mTableNumber;
    }

    public void setTableNumber(int tableNumber) {
        mTableNumber = tableNumber;
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

    public String getAllergensString(){
        String result = "";
        if (mAllergens.size() > 0){
            for (Allergen allergen : mAllergens) {
                result = result + allergen.getName() + ", ";
            }
            return result.substring(0, result.length() - 2);
        }
        return result;
    }
}
