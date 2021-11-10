package com.example.assignment2_skeletonproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String mUrl = "https://raw.githubusercontent.com/RaniaArbash/Assignment2_SkeletonProject/master/cars.json";
    private RecyclerView mRecyclerView;
    private ProgressBar mLoading;
    private TextView mError;
    private List<Car> mCarList = new ArrayList<>();
    private CarAdapter mAdapter = new CarAdapter(mCarList);
    private DataBaseManager mDataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.car_list);
        mLoading = findViewById(R.id.progress_bar);
        mError = findViewById(R.id.tv_error);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBaseManager = DataBaseManager.getInstance(this);
        mAdapter.setItemCarClickListener(new CarAdapter.OnItemCarClick() {
            @Override
            public void onItemCarClicked(int position) {
                Car car = mCarList.get(position);
                if (car.isIsFavorite()) {
                    car.setIsFavorite(false);
                    mDataBaseManager.carDao().insertCar(car);
                    car.setIsFavorite(false);
                    mAdapter.notifyItemChanged(position);
                    return;
                }
                showFavoriteDialog(position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        init();
    }

    private void init() {
        mLoading.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = new NetworkingManager().getJsonFromUrl(mUrl);
                if (TextUtils.isEmpty(json)){
                    onError();
                    return;
                }
                List<Car> carList = JsonManager.parsJsonToCarList(json);
                MainActivity.this.onSuccess(carList);
            }
        }).start();
    }

    private void onSuccess(final List<Car> carList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isDestroyed()) return;
                List<Car> list = mDataBaseManager.carDao().getAll();
                for (Car car : carList) {
                    boolean favorite = false;
                    for (Car car1 : list) {
                        if (car1.getId() == car.getId() && car1.isIsFavorite()){
                            favorite = true;
                            break;
                        }
                    }
                    car.setIsFavorite(favorite);
                }
                mCarList.clear();
                mCarList.addAll(carList);
                mAdapter.notifyDataSetChanged();
                mLoading.setVisibility(View.GONE);
            }
        });
    }

    private void onError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(isDestroyed()) return;
                mError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showFavoriteDialog(final int position) {
        final Car car = mCarList.get(position);
        new AlertDialog.Builder(this)
                .setTitle("Favorite Car ?")
                .setMessage("Are you sure you want to insert " + car.getCarModel1() + " to DataBase?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        car.setIsFavorite(true);
                        mDataBaseManager.carDao().insertCar(car);
                        mCarList.get(position).setIsFavorite(true);
                        mAdapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
