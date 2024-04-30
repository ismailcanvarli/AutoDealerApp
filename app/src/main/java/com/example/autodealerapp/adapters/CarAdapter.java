package com.example.autodealerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.R;
import com.example.autodealerapp.data.ModelCar;
import com.example.autodealerapp.ui.CarDetails;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final Context context;
    private final ArrayList<ModelCar> modelCarList;

    public CarAdapter(Context context, ArrayList<ModelCar> modelCarList) {
        this.context = context;
        this.modelCarList = modelCarList;
    }

    // View nesnesini oluştur ve view nesnelerini bağla
    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View nesnesini oluştur
        View view = LayoutInflater.from(context).inflate(R.layout.row_car_item, parent, false);
        CarViewHolder viewHolder = new CarViewHolder(view);
        return viewHolder;
    }

    // Araç listesinden pozisyonu al ve view nesnelerine ata
    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        // Araç listesinden pozisyonu al
        ModelCar modelCar = modelCarList.get(position);

        // Araç özelliklerini view nesnelerine ata
        String id = modelCar.getId();
        String brand = modelCar.getBrand();
        String model = modelCar.getModel();
        String color = modelCar.getColor();
        String year = modelCar.getYear();
        String kilometer = modelCar.getKilometer();
        String price = modelCar.getPrice();

        holder.brandTextView.setText(brand);
        holder.modelTextView.setText(model);
        holder.colorTextView.setText(color);
        holder.yearTextView.setText(year);
        holder.kilometerTextView.setText(kilometer);
        holder.priceTextView.setText(price);


        // View nesnelerine araç özelliklerini ata
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CarDetails.class);
            intent.putExtra("carId", id);
            context.startActivity(intent);
        });

    }

    // Araç listesinin boyutunu döndür
    @Override
    public int getItemCount() {
        return modelCarList.size();
    }

    // Araç listesini tutacak değişken tanımla
    static class CarViewHolder extends RecyclerView.ViewHolder {
        // View nesnelerini tanımla
        TextView brandTextView;
        TextView modelTextView;
        TextView colorTextView;
        TextView yearTextView;
        TextView kilometerTextView;
        TextView priceTextView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            // View nesnelerini bağla
            brandTextView = itemView.findViewById(R.id.brandTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            colorTextView = itemView.findViewById(R.id.colorTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            kilometerTextView = itemView.findViewById(R.id.kilometerTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
        }

    }

}