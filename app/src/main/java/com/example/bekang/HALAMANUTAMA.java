package com.example.bekang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.List;

public class HALAMANUTAMA extends AppCompatActivity implements PermissionsListener {

    private FirebaseAuth firebaseAuth;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoic2FtcHVybmlhIiwiYSI6ImNrYTZjcGFpZjA2dzUzMG13NGNxdHVldGEifQ.hJkKxmZcvSZisv4N4PvHLg");
        setContentView(R.layout.activity_halamanutama);
        firebaseAuth = FirebaseAuth.getInstance();
        Mapbox.getInstance(this, getString(R.string.access_token));
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                HALAMANUTAMA.this.mapboxMap = mapboxMap;

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                            @Override
                            public void onStyleLoaded(@NonNull Style style) {
                                enableLocationComponent(HALAMANUTAMA.this, style);
                            }
                        });
            }
        });

    }


    @SuppressWarnings( {"MissingPermission"})
    private static void enableLocationComponent(HALAMANUTAMA mainActivity, @NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(mainActivity)) {
// Get an instance of the component
            LocationComponent locationComponent = mainActivity.mapboxMap.getLocationComponent();
// Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(mainActivity, loadedMapStyle).build());
// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);
// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);
// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            mainActivity.permissionsManager = new PermissionsManager(mainActivity);
            mainActivity.permissionsManager.requestLocationPermissions(mainActivity);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(HALAMANUTAMA.this, style);
                }
            });
        } else {
            Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HALAMANUTAMA.this, HALAMANLOGIN.class));
    }

    private void Refresh() {
        Intent intent = new Intent(HALAMANUTAMA.this, HALAMANUTAMA.class);
        startActivity(intent);
        finish();
    }

    private void List() {
        Intent intent = new Intent(HALAMANUTAMA.this, HALAMANSTATUS.class);
        startActivity(intent);
        finish();
    }

    private void Info() {
        Intent intent = new Intent(HALAMANUTAMA.this, HALAMANINFO.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutMenu:
                Logout();
                break;
            case R.id.RefreshMenu:
                Refresh();
                break;
            case R.id.ListMenu:
                List();
                break;
            case R.id.InfoMenu:
                Info();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
