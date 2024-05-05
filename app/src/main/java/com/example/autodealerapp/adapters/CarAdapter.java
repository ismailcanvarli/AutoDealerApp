package com.example.autodealerapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autodealerapp.MainActivity;
import com.example.autodealerapp.R;
import com.example.autodealerapp.data.DbHelper;
import com.example.autodealerapp.data.ModelCar;
import com.example.autodealerapp.ui.AddEditCar;
import com.example.autodealerapp.ui.CarDetails;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final Context context;
    private final ArrayList<ModelCar> modelCarList;
    // Veritabanı yardımcı sınıfını tanımla
    private final DbHelper dbHelper;

    public CarAdapter(Context context, ArrayList<ModelCar> modelCarList) {
        this.context = context;
        this.modelCarList = modelCarList;
        // Veritabanı yardımcı sınıfını oluştur
        dbHelper = new DbHelper(context);
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
        String fuel = modelCar.getFuel();
        String gearbox = modelCar.getGearBox();
        String price = modelCar.getPrice();
        String addedTime = modelCar.getAddedTime();
        String updatedTime = modelCar.getUpdatedTime();

        holder.brandTextView.setText(brand);
        holder.modelTextView.setText(model);
        holder.colorTextView.setText(color);
        holder.yearTextView.setText(year);
        holder.kilometerTextView.setText(kilometer);
        holder.fuelTextView.setText(fuel);
        holder.gearboxTextView.setText(gearbox);
        holder.priceTextView.setText(price);


        // View nesnelerine araç özelliklerini ata
        holder.tableLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarDetails.class);
                intent.putExtra("carId", id);
                context.startActivity(intent);
            }
        });

        // Araç düzenleme sayfasına git
        holder.carEditTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddEditCar.class);
                // Araç özelliklerini intent ile gönder
                intent.putExtra("ID", id);
                intent.putExtra("BRAND", brand);
                intent.putExtra("MODEL", model);
                intent.putExtra("COLOR", color);
                intent.putExtra("YEAR", year);
                intent.putExtra("KILOMETER", kilometer);
                intent.putExtra("FUEL", fuel);
                intent.putExtra("GEARBOX", gearbox);
                intent.putExtra("PRICE", price);
                intent.putExtra("ADDED_TIME", addedTime);
                intent.putExtra("UPDATED_TIME", updatedTime);

                // Düzenleme modunda olduğunu belirt
                intent.putExtra("isEditMode", true);

                // Araç düzenleme sayfasına git
                context.startActivity(intent);
            }
        });

        // Araç silme işlemi
        holder.carDeleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Güncel pozisyonu al
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // Veritabanından araç sil
                    String id = modelCarList.get(currentPosition).getId();
                    dbHelper.deleteCar(id);

                    // Araç listesini güncelle
                    ((MainActivity) context).onResume();

                    // Araç listesini güncelle
                    modelCarList.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, modelCarList.size());
                }
            }
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
        TextView fuelTextView;
        TextView gearboxTextView;
        TextView priceTextView;
        TextView carEditTextView;
        TextView carDeleteTextView;
        TableLayout tableLayout1;
        TableLayout tableLayout2;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            // View nesnelerini bağla
            brandTextView = itemView.findViewById(R.id.brandTextView);
            modelTextView = itemView.findViewById(R.id.modelTextView);
            colorTextView = itemView.findViewById(R.id.colorTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            kilometerTextView = itemView.findViewById(R.id.kilometerTextView);
            fuelTextView = itemView.findViewById(R.id.fuelTextView);
            gearboxTextView = itemView.findViewById(R.id.gearboxTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            carEditTextView = itemView.findViewById(R.id.carEditTextView);
            carDeleteTextView = itemView.findViewById(R.id.carDeleteTextView);
            tableLayout1 = itemView.findViewById(R.id.mainLayout1);
            tableLayout2 = itemView.findViewById(R.id.mainLayout2);

        }

    }

}