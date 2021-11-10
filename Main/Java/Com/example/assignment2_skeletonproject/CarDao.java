package com.example.assignment2_skeletonproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDao {

    @Query("select * from Car")
    List<Car> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCar(Car car);


}
