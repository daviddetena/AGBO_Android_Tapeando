package com.daviddetena.tapeando.model;

import com.daviddetena.tapeando.R;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private int mId;
    private int mMenuId;
    private int mTableNumber;
    private String mName;
    private String mType;
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

    /**
     * Constructor to init a new instance of Course when fetched from JSON
     * @param id
     * @param menuId
     * @param name
     * @param type
     * @param photo
     * @param photoUrl
     * @param description
     * @param allergens
     * @param price
     */
    public Course(int id, int menuId, String name, String type, String photo, String photoUrl,
                  String description, List<Allergen> allergens, float price){
        this.mId = id;
        this.mMenuId = menuId;
        this.mTableNumber = 0;
        this.mName = name;
        this.mType = type;
        this.mPhoto = photo;
        this.mPhotoUrl = photoUrl;
        this.mDescription = description;
        this.mAllergens = allergens;
        this.mPrice = price;
        this.mNotes = "";
    }

    /**
     * This constructor is used when the course is added to a table
     * @param id
     * @param menuId
     * @param tableNumber
     * @param name
     * @param type
     * @param photo
     * @param photoUrl
     * @param description
     * @param allergens
     * @param price
     * @param notes
     */
    public Course(int id, int menuId, int tableNumber, String name, String type, String photo,
                  String photoUrl, String description, List<Allergen> allergens, float price,
                  String notes){
        this.mId = id;
        this.mMenuId = menuId;
        this.mTableNumber = tableNumber;
        this.mName = name;
        this.mType = type;
        this.mPhoto = photo;
        this.mPhotoUrl = photoUrl;
        this.mDescription = description;
        this.mAllergens = allergens;
        this.mPrice = price;
        this.mNotes = notes;
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

    public int getMenuId() {
        return mMenuId;
    }

    public void setMenuId(int menuId) {
        mMenuId = menuId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
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
