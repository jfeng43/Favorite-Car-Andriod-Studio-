package com.example.assignment2_skeletonproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Car {

    /**
     * id : 1
     * CarModel1 : Nissan
     * CarModel2 : Altima
     * Year : 2015
     */

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String CarModel1;
    private String CarModel2;
    private boolean IsFavorite;

    public Car() {
    }

    public Car(int id, String carModel1, String carModel2, boolean isFavorite) {
        this.id = id;
        CarModel1 = carModel1;
        CarModel2 = carModel2;
        IsFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarModel1() {
        return CarModel1;
    }

    public void setCarModel1(String CarModel1) {
        this.CarModel1 = CarModel1;
    }

    public String getCarModel2() {
        return CarModel2;
    }

    public void setCarModel2(String CarModel2) {
        this.CarModel2 = CarModel2;
    }

    public boolean isIsFavorite() {
        return IsFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        IsFavorite = favorite;
    }

}
