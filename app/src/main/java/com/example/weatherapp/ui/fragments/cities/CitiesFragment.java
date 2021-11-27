package com.example.weatherapp.ui.fragments.cities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FragmentCitiesBinding;
import com.example.weatherapp.ui.base.BaseFragment;

public class CitiesFragment extends BaseFragment<FragmentCitiesBinding> {

    public CitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected FragmentCitiesBinding bind() {
        return FragmentCitiesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        binding.btnSelect.setOnClickListener(v -> {
            if (binding.etText.getText() != null){
                String city = binding.etText.getText().toString();
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                navController.navigate((NavDirections) CitiesFragmentDirections.actionCitiesFragmentToMainFragment22(city));
            }
        });
    }

    @Override
    protected void setupObservers() {

    }
}