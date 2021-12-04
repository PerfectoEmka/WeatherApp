package com.example.weatherapp.ui.fragments;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.models.daily.forecast;
import com.example.weatherapp.databinding.ItemDailyForecastBinding;

import java.text.SimpleDateFormat;
import java.util.List;

public class DailyForeCastAdapter extends RecyclerView.Adapter<DailyForeCastAdapter.ViewHolder>{

    private List<forecast> list;

    public void setList(List<forecast> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDailyForecastBinding binding = ItemDailyForecastBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemDailyForecastBinding binding;

        public ViewHolder(@NonNull ItemDailyForecastBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(forecast forecast) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd");
            binding.dateTV.setText(sdf.format(forecast.time));
        }
    }
}
