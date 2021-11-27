package com.example.weatherapp.ui.fragments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.databinding.ItemDailyForecastBinding;

import java.util.List;

public class DailyForeCastAdapter extends RecyclerView.Adapter<DailyForeCastAdapter.ViewHolder>{

    private List<Weather> list;

    public void setList(List<Weather> list) {
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

        public void onBind(Weather weather) {
            binding.dateTV.setText(weather.getMain());
        }
    }
}
