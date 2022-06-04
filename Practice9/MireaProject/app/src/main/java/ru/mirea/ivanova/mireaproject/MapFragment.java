package ru.mirea.ivanova.mireaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MapFragment extends Fragment implements GoogleMap.OnMapClickListener {
    private GoogleMap map;
    private ArrayList<MarkerOptions> mapMarkers = new ArrayList<>();
    private final String name = "РТУ МИРЭА";
    private static final int REQUEST_CODE_PERMISSION = 100;
    private final String[] PERMISSIONS =
            {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
    private boolean isWork;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            map.setOnMapClickListener(MapFragment.this::onMapClick);

            isWork = hasPermissions(getActivity(), PERMISSIONS);
            if (!isWork) {
                ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, REQUEST_CODE_PERMISSION);
            }

            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map.getUiSettings().setZoomControlsEnabled(true);

            LatLng main = new LatLng(55.670005, 37.479894);
            googleMap.addMarker(new MarkerOptions().position(main).title(name + "(55.670005, 37.479894)").snippet("г. Москва, Проспект Вернадского, д. 78"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(main).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            LatLng him = new LatLng(55.661445, 37.477049);
            googleMap.addMarker(new MarkerOptions().position(him).title(name + "(55.661445, 37.477049)").snippet("г. Москва, Проспект Вернадского, д. 86"));

            LatLng strom = new LatLng(55.794259, 37.701448);
            googleMap.addMarker(new MarkerOptions().position(strom).title(name + "(55.794259, 37.701448)").snippet("г. Москва, ул. Стромынка, д.20"));

            LatLng stavropol = new LatLng(45.049513, 41.912041);
            googleMap.addMarker(new MarkerOptions().position(stavropol).title(name + "(45.049513, 41.912041)").snippet("г. Ставрополь, пр. Кулакова, д. 8"));

            LatLng phrazefo = new LatLng(55.966853, 38.050774);
            googleMap.addMarker(new MarkerOptions().position(phrazefo).title(name + "(55.966853, 38.050774)").snippet(" г. Фрязино, ул. Вокзальная, д. 2а"));
        }
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED)
                    return false;
            }
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    private void addNewMarker(Date date, LatLng latLng) {
        MarkerOptions marker = new MarkerOptions().title("Новое место").snippet(date.toString()).position(latLng);
        map.addMarker(marker);
        mapMarkers.add(marker);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Date currentTime = Calendar.getInstance().getTime();
        latLng.toString();
        addNewMarker(currentTime, latLng);
    }
}