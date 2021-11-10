package com.example.assignment2_skeletonproject;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonManager {

    public static List<Car> parsJsonToCarList(String json) {
        ArrayList<Car> carArrayList = new ArrayList<>();
        if (TextUtils.isEmpty(json)) return carArrayList;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.optInt("id");
                String carModel1 = object.optString("CarModel1");
                String carModel2 = object.optString("CarModel2");
                Car car = new Car(id, carModel1, carModel2, false);
                carArrayList.add(car);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return carArrayList;
    }

}
