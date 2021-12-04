package com.example.weatherapp.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.models.Main;
import com.example.weatherapp.data.models.Sys;
import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.models.Wind;
import com.example.weatherapp.data.models.daily.forecast;
import com.example.weatherapp.databinding.FragmentMainBinding;
import com.example.weatherapp.ui.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainFragmentArgs args;
    private MainFragmentViewModel viewModel;
    private Main main;
    private Wind wind;
    private Example example;
    private Sys sys;
    private List<Weather> weatherList = new ArrayList<>();
    private DailyForeCastAdapter adapter;
    @Inject
    WeatherDao dao;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DailyForeCastAdapter();
        args = MainFragmentArgs.fromBundle(getArguments());
    }

    @Override
    protected FragmentMainBinding bind() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(requireActivity()).get(MainFragmentViewModel.class);
        viewModel.fetchCurrentForeCast(args.getCityName());
        viewModel.fetchDailyForeCast(args.getCityName());

        binding.cityBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
            navController.navigate(R.id.action_mainFragment2_to_citiesFragment2);
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Example>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<Example> exampleResource) {
                switch (exampleResource.status){
                    case  SUCCESS:{
                        main = exampleResource.data.getMain();
                        wind = exampleResource.data.getWind();
                        example =  exampleResource.data;
                        sys = exampleResource.data.getSys();
                        weatherList = exampleResource.data.getWeather();
                        setData();
                        break;
                    }
                    case LOADING:{
                        binding.progress.setVisibility(View.VISIBLE);
                        break;
                    }
                    case ERROR:{
                        binding.progress.setVisibility(View.GONE);
                        if (dao.getWeather() != null){
                            main = dao.getWeather().getMain();
                            wind = dao.getWeather().getWind();
                            example =  dao.getWeather();
                            sys = dao.getWeather().getSys();
                            weatherList = dao.getWeather().getWeather();
                            setData();
                        }
                        break;
                    }
                }
            }

            private void setData() {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

                binding.sunnyTv.setText(weatherList.get(0).getMain());
                binding.cityBtn.setText(example.getName());
                binding.currentTempTV.setText(String.valueOf((int) Math.round(main.getTemp())));
                binding.minDegreeTV.setText(String.valueOf((int) Math.round(main.getTemp())));
                binding.maxDegreeTV.setText(String.valueOf((int) Math.round(main.getTemp())));
                binding.progress.setVisibility(View.GONE);
                Glide.with(requireContext())
                        .load("https://openweathermap.org/img/wn/" + weatherList.get(0).getIcon() + ".png")
                        .into(binding.weatherIV);
                binding.humidityTV.setText(main.getHumidity() + "%");
                binding.pressureTV.setText(main.getPressure() + "mBar");
                binding.sunriseTV.setText(sdf.format(sys.getSunrise()));
                binding.sunsetTV.setText(sdf.format(sys.getSunset()));
                binding.windTV.setText(wind.getSpeed() +  "m/s");
                binding.dayTimeTV.setText(sdf.format(example.getDt()));
            }
        });
        viewModel.dailyLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<List<forecast>>>() {
            @Override
            public void onChanged(Resource<List<forecast>> listResource) {
                switch (listResource.status){
                    case SUCCESS:{
                        adapter.setList(listResource.data);
                        binding.recycler.setAdapter(adapter);
                        break;
                    }
                    case ERROR:{
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING:{
                        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }
}