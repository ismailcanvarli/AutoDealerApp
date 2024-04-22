package com.example.autodealerapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.R;
import com.example.autodealerapp.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private List<Car> carList;
    private OnItemClickListener listener;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.bind(car);
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView brandTextView;
        private TextView modelTextView;
        private TextView yearTextView;
        private TextView kilometerTextView;
        private TextView colorTextView;
        private TextView priceTextView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            brandTextView = itemView.findViewById(R.id.brandTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            kilometerTextView = itemView.findViewById(R.id.kilometerTextView);
            colorTextView = itemView.findViewById(R.id.colorTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(@NonNull Car car) {
            brandTextView.setText("Brand: " + car.getBrand());
            modelTextView.setText("Model: " + car.getModel());
            yearTextView.setText("Year: " + car.getYear());
            kilometerTextView.setText("Kilometer: " + car.getKilometer());
            colorTextView.setText("Color: " + car.getColor());
            priceTextView.setText("Price: " + car.getPrice());
        }
    }
}