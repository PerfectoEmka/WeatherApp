package com.example.weatherapp.ui.fragments.cities;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.weatherapp.R;
import com.example.weatherapp.databinding.FragmentCitiesBinding;
import com.example.weatherapp.ui.base.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class CitiesFragment extends BaseFragment<FragmentCitiesBinding> implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private final String[] perms = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private LocationManager locationManager;
    private ArrayList<LatLng> list = new ArrayList<>();

    public CitiesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        SupportMapFragment mapFragment = (SupportMapFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected FragmentCitiesBinding bind() {
        return FragmentCitiesBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupUI() {
        binding.btnSelect.setOnClickListener(v -> {
            if (binding.etText.getText() != null) {
                String city = binding.etText.getText().toString();
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                navController.navigate((NavDirections) CitiesFragmentDirections.actionCitiesFragmentToMainFragment22(city));
            }
        });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission
                (requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0,
                    0,
                    this
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (
                    grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED
                            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                ActivityCompat.requestPermissions(requireActivity(), permissions, 1);
            }
        }
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ActivityCompat.requestPermissions(requireActivity(), perms, 1);
        getCurrentLocation();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e(TAG, "onMapClick: " + latLng.toString());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.addMarker(markerOptions);
                list.add(latLng);
                mMap.addPolyline(new PolylineOptions()
                        .addAll(list)
                        .width(4)
                        .color(Color.BLUE)
                );
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                        CameraPosition.builder()
                                .zoom(6f)
                                .target(latLng)
                                .build()
                ));
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        MarkerOptions options = new MarkerOptions();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        options.position(latLng);
        mMap.clear();
        mMap.addMarker(options);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition
                .builder()
                .target(latLng)
                .build()
        ));
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}