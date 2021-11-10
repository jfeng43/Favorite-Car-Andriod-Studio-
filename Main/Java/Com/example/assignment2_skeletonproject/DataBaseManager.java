package com.example.assignment2_skeletonproject;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class}, version = 1,exportSchema = false)
public abstract class DataBaseManager extends RoomDatabase {

    private static DataBaseManager instance;

    public abstract CarDao carDao();

    public static DataBaseManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DataBaseManager.class) {
                if (instance == null) {
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    private static DataBaseManager create(Context context) {
        return Room.databaseBuilder(context, DataBaseManager.class, "car-db").allowMainThreadQueries().build();
    }

}
