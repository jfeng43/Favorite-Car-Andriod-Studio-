package com.example.assignment2_skeletonproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {

    private List<Car> mCarList;
    private OnItemCarClick mListener;

    public CarAdapter(List<Car> carList) {
        this.mCarList = carList;
    }

    public interface OnItemCarClick {
        void onItemCarClicked(int position);
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_list, parent, false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, final int position) {
        final Car car = mCarList.get(position);
        holder.title.setText(car.getCarModel1());
        holder.subTitle.setText(car.getCarModel2());
        if (car.isIsFavorite()){
            holder.like.setImageResource(R.drawable.like);
        }else {
            holder.like.setImageResource(R.drawable.unlike);
        }
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemCarClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public void setItemCarClickListener(OnItemCarClick listener) {
        this.mListener = listener;
    }

    class CarHolder extends RecyclerView.ViewHolder {

        ImageView like;
        TextView title;
        TextView subTitle;

        public CarHolder(@NonNull View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.img_like_status);
            title = itemView.findViewById(R.id.tv_title);
            subTitle = itemView.findViewById(R.id.tv_subtitle);
        }
    }

}
